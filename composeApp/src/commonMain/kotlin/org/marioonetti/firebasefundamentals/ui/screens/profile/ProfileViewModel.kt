package org.marioonetti.firebasefundamentals.ui.screens.profile

import kotlinx.coroutines.launch
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.repository.UserRepository
import org.marioonetti.firebasefundamentals.ui.RootViewModel
import org.marioonetti.firebasefundamentals.ui.ViewEffect
import org.marioonetti.firebasefundamentals.ui.ViewEvent
import org.marioonetti.firebasefundamentals.ui.ViewState

class ProfileViewModel(
    private val userRepository: UserRepository
): RootViewModel<ProfileState, ProfileEvent, ProfileEffect>(ProfileState.Loading) {

    init {
        fetchUserData()
    }

    override fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.OnLogout -> {
                updateState { ProfileState.Loading }
                vmScope.launch {
                    userRepository.logOut().fold(
                        success = {
                            vmScope.launch {
                                setEffect(ProfileEffect.NavigateToLogin)
                            }
                        },
                        error = { error ->
                            updateState { ProfileState.Error(error) }
                        }
                    )
                }
            }
            is ProfileEvent.OnTryAgain -> {
                updateState { ProfileState.Loading }
                fetchUserData()
            }
        }
    }

    private fun fetchUserData() {
        vmScope.launch {
            userRepository.getUserData().fold(
                success = { user ->
                    updateState { ProfileState.Idle(userName = user.username, email = user.email) }
                },
                error = {
                    updateState { ProfileState.Error(it) }
                }
            )
        }
    }

}

sealed class ProfileState: ViewState() {
    data object Loading : ProfileState()
    data class Error(val error: AppError) : ProfileState()
    data class Idle(val userName: String, val email: String) : ProfileState()
}

sealed class ProfileEvent: ViewEvent() {
    data object OnLogout : ProfileEvent()
    data object OnTryAgain : ProfileEvent()
}

sealed class ProfileEffect: ViewEffect() {
    data object NavigateToLogin : ProfileEffect()
}