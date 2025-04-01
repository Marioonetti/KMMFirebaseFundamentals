package org.marioonetti.firebasefundamentals.ui.screens.register

import kotlinx.coroutines.launch
import org.marioonetti.firebasefundamentals.data.model.UserRequestDto
import org.marioonetti.firebasefundamentals.domain.repository.UserRepository
import org.marioonetti.firebasefundamentals.ui.RootViewModel
import org.marioonetti.firebasefundamentals.ui.ViewEffect
import org.marioonetti.firebasefundamentals.ui.ViewEvent
import org.marioonetti.firebasefundamentals.ui.ViewState

class RegisterViewModel(
    private val userRepository: UserRepository,
) : RootViewModel<RegisterState, RegisterEvent, RegisterEffect>(RegisterState.Idle()) {

    override fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnEmailChanged -> {
                if (uiState.value is RegisterState.Idle) {
                    updateState { (this as RegisterState.Idle).copy(email = event.newEmail) }
                }
            }

            is RegisterEvent.OnPasswordChanged -> updateState {
                (this as? RegisterState.Idle)?.copy(password = event.newPassword) ?: this
            }

            is RegisterEvent.OnUsernameChanged -> updateState {
                (this as? RegisterState.Idle)?.copy(userName = event.newUserName) ?: this
            }
            is RegisterEvent.OnRegister -> {
                if (uiState.value is RegisterState.Idle) {
                    handleRegister()
                }
            }
            else -> {}
        }
    }

    private fun handleRegister() {
        val state = uiState.value as RegisterState.Idle
        val userReq = UserRequestDto(
            state.email.trim(),
            state.userName,
            state.password.trim(),
        )
        vmScope.launch {
            userRepository.register(userReq).fold(
                error = {
                    println("Error $it")
                },
                success = {
                    vmScope.launch {
                        setEffect(RegisterEffect.OnNavigateToHome)
                    }
                }
            )
        }
    }
}

sealed class RegisterState : ViewState() {
    data class Idle(
        val email: String = "",
        val userName: String = "",
        val password: String = "",
    ) : RegisterState()
}

sealed class RegisterEvent : ViewEvent() {
    data class OnEmailChanged(val newEmail: String) : RegisterEvent()
    data class OnPasswordChanged(val newPassword: String) : RegisterEvent()
    data class OnUsernameChanged(val newUserName: String) : RegisterEvent()

    data object OnCheckFields : RegisterEvent()
    data object OnRegister : RegisterEvent()
}

sealed class RegisterEffect : ViewEffect() {
    data object OnNavigateToHome : RegisterEffect()
}
