package org.marioonetti.firebasefundamentals.ui.screens.splash

import kotlinx.coroutines.launch
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.repository.UserRepository
import org.marioonetti.firebasefundamentals.ui.RootViewModel
import org.marioonetti.firebasefundamentals.ui.ViewEffect
import org.marioonetti.firebasefundamentals.ui.ViewEvent
import org.marioonetti.firebasefundamentals.ui.ViewState

class SplashViewModel(
    private val userRepository: UserRepository,
): RootViewModel<SplashState, SplashEvent, SplashEffect>(SplashState.Loading) {

    init {
        checkUserAccess()
    }

    override fun onEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.OnTryAgain -> {
                updateState { SplashState.Loading }
                checkUserAccess()
            }
        }
    }

    private fun checkUserAccess() {
        if (uiState.value is SplashState.Loading) {
            vmScope.launch {
                userRepository.isUserLoggedIn()
                    .fold(
                        error = {
                            updateState { SplashState.Error(it) }
                        },
                        success = {
                            val effect = if (it) {
                                SplashEffect.NavigateToHome
                            } else {
                                SplashEffect.NavigateToLogin
                            }
                            vmScope.launch { setEffect(effect) }

                        }
                    )
            }
        }
    }
}

sealed class SplashState : ViewState() {
    data object Loading : SplashState()
    data class Error(val message: AppError) : SplashState()
}

sealed class SplashEvent : ViewEvent() {
    data object OnTryAgain : SplashEvent()
}

sealed class SplashEffect : ViewEffect() {
    data object NavigateToHome : SplashEffect()
    data object NavigateToLogin : SplashEffect()
}