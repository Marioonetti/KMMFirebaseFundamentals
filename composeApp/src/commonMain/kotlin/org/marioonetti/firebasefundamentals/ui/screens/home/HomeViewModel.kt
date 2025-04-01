package org.marioonetti.firebasefundamentals.ui.screens.home

import kotlinx.coroutines.launch
import org.marioonetti.firebasefundamentals.domain.repository.UserRepository
import org.marioonetti.firebasefundamentals.ui.RootViewModel
import org.marioonetti.firebasefundamentals.ui.ViewEffect
import org.marioonetti.firebasefundamentals.ui.ViewEvent
import org.marioonetti.firebasefundamentals.ui.ViewState

class HomeViewModel(
    private val userRepository: UserRepository,
) : RootViewModel<HomeState, HomeEvent, HomeEffect>(HomeState.Idle) {

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnLogOut -> {
                vmScope.launch {
                    userRepository.logOut().fold(
                        error = {
                            println("Error $it")
                        },
                        success = {
                            vmScope.launch {
                                setEffect(HomeEffect.NavigateToLogin)
                            }
                        }
                    )
                }
            }
        }
    }
}


sealed class HomeState: ViewState() {
    data object Idle: HomeState()
}

sealed class HomeEvent : ViewEvent() {
    data object OnLogOut : HomeEvent()
}

sealed class HomeEffect : ViewEffect() {
    data object NavigateToLogin : HomeEffect()
}