package org.marioonetti.firebasefundamentals.ui.screens.login

import kotlinx.coroutines.launch
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.repository.UserRepository
import org.marioonetti.firebasefundamentals.ui.RootViewModel
import org.marioonetti.firebasefundamentals.ui.ViewEffect
import org.marioonetti.firebasefundamentals.ui.ViewEvent
import org.marioonetti.firebasefundamentals.ui.ViewState

class LoginViewModel(
    private val userRepository: UserRepository,
) : RootViewModel<LoginState, LoginEvent, LoginEffect>(LoginState.Idle()) {

    override fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> {
                if (uiState.value is LoginState.Idle) {
                    updateState { (this as LoginState.Idle).copy(email = event.newEmail) }
                }
            }
            is LoginEvent.OnPasswordChanged -> {
                if (uiState.value is LoginState.Idle) {
                    updateState {
                        (this as? LoginState.Idle)?.copy(password = event.newPassword) ?: this
                    }
                }
            }
            is LoginEvent.OnCheckFields -> {
                if (uiState.value is LoginState.Idle) {

                }
            }
            is LoginEvent.OnNavigateToRegister -> {
                vmScope.launch {
                    setEffect(LoginEffect.OnNavigateToRegister)
                }
            }
            is LoginEvent.OnLogIn -> {
                val currentState = uiState.value
                if (currentState is LoginState.Idle) {
                    updateState { currentState.copy(showLoading = true) }
                    vmScope.launch {
                        userRepository.logIn(
                            currentState.email.trim(),
                            currentState.password.trim()
                        ).fold(
                            error = {
                                updateState { LoginState.Error(it) }
                            },
                            success = {
                                vmScope.launch {
                                    setEffect(LoginEffect.OnNavigateToHome)
                                }
                            }
                        )
                    }
                }
            }
            is LoginEvent.OnClose -> updateState { LoginState.Idle() }
        }
    }

}

sealed class LoginState: ViewState() {
    data class Idle(
        val email: String = "",
        val password: String = "",
        val showLoading: Boolean = false,
    ): LoginState()

    data class Error(
        val error: AppError
    ): LoginState()
}

sealed class LoginEvent : ViewEvent() {
    data class OnEmailChanged(val newEmail: String) : LoginEvent()
    data class OnPasswordChanged(val newPassword: String) : LoginEvent()
    data object OnCheckFields : LoginEvent()

    data object OnLogIn : LoginEvent()
    data object OnNavigateToRegister : LoginEvent()
    data object OnClose : LoginEvent()
}

sealed class LoginEffect : ViewEffect() {
    data object OnNavigateToHome : LoginEffect()
    data object OnNavigateToRegister : LoginEffect()
}