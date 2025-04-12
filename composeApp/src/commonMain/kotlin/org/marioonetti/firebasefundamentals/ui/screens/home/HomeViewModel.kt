package org.marioonetti.firebasefundamentals.ui.screens.home

import kotlinx.coroutines.launch
import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonDto
import org.marioonetti.firebasefundamentals.domain.repository.DigimonRepository
import org.marioonetti.firebasefundamentals.domain.repository.UserRepository
import org.marioonetti.firebasefundamentals.ui.RootViewModel
import org.marioonetti.firebasefundamentals.ui.ViewEffect
import org.marioonetti.firebasefundamentals.ui.ViewEvent
import org.marioonetti.firebasefundamentals.ui.ViewState

class HomeViewModel(
    private val userRepository: UserRepository,
    private val digimonRepository: DigimonRepository,
) : RootViewModel<HomeState, HomeEvent, HomeEffect>(HomeState.Loading) {

    init {
        vmScope.launch {
            digimonRepository.getRandomDigimon().fold(
                error = {
                    println("Error $it")
                },
                success = { digimonList ->
                    updateState { (HomeState.Idle(digimonList)) }
                }
            )
        }
    }

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

            is HomeEvent.OnDigimonClick -> {
                vmScope.launch {
                    setEffect(HomeEffect.NavigateToDetail(event.digimonName))
                }
            }
        }
    }
}


sealed class HomeState: ViewState() {
    data class Idle(
        val digimonList: List<DigimonDto> = emptyList(),
    ): HomeState()
    data object Loading: HomeState()
}

sealed class HomeEvent : ViewEvent() {
    data object OnLogOut : HomeEvent()
    data class OnDigimonClick(
        val digimonName: String,
    ) : HomeEvent()
}

sealed class HomeEffect : ViewEffect() {
    data object NavigateToLogin : HomeEffect()
    data class NavigateToDetail(
        val digimonName: String,
    ) : HomeEffect()
}