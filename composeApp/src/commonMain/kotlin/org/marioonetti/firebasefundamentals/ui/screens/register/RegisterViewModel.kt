package org.marioonetti.firebasefundamentals.ui.screens.register

import kotlinx.coroutines.launch
import org.marioonetti.firebasefundamentals.data.model.firebase.UserRequestDto
import org.marioonetti.firebasefundamentals.domain.core.AppError
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
            is RegisterEvent.OnConfirmPasswordChanged -> updateState {
                (this as? RegisterState.Idle)?.copy(confirmPassword = event.newPassword) ?: this
            }
            is RegisterEvent.OnUsernameChanged -> updateState {
                (this as? RegisterState.Idle)?.copy(userName = event.newUserName) ?: this
            }
            is RegisterEvent.OnRegister -> {
                if (uiState.value is RegisterState.Idle) {
                    handleRegister()
                }
            }
            is RegisterEvent.OnNavigateToLogin -> {
                vmScope.launch {
                    setEffect(RegisterEffect.OnNavigateToLogin)
                }
            }
            is RegisterEvent.OnTryAgain -> {
                updateState { RegisterState.Idle() }
            }
            is RegisterEvent.OnCheckFields -> {}
        }
    }

    private fun handleRegister() {
        val state = uiState.value as RegisterState.Idle
        updateState { state.copy(showLoading = true) }
        val userReq = UserRequestDto(
            state.email.trim(),
            state.userName,
            state.password.trim(),
        )
        vmScope.launch {
            userRepository.register(userReq).fold(
                error = {
                    updateState { RegisterState.Error(it) }
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
        val confirmPassword: String = "",
        val showLoading: Boolean = false
    ) : RegisterState()
    data class Error(val error: AppError) : RegisterState()
}

sealed class RegisterEvent : ViewEvent() {
    data class OnEmailChanged(val newEmail: String) : RegisterEvent()
    data class OnPasswordChanged(val newPassword: String) : RegisterEvent()
    data class OnConfirmPasswordChanged(val newPassword: String) : RegisterEvent()
    data class OnUsernameChanged(val newUserName: String) : RegisterEvent()

    data object OnCheckFields : RegisterEvent()
    data object OnRegister : RegisterEvent()
    data object OnNavigateToLogin : RegisterEvent()
    data object OnTryAgain : RegisterEvent()
}

sealed class RegisterEffect : ViewEffect() {
    data object OnNavigateToHome : RegisterEffect()
    data object OnNavigateToLogin : RegisterEffect()
}
