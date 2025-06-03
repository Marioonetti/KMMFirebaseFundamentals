package org.marioonetti.firebasefundamentals.ui.screens.home

import kotlinx.coroutines.launch
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi
import org.marioonetti.firebasefundamentals.domain.repository.DigimonRepository
import org.marioonetti.firebasefundamentals.ui.RootViewModel
import org.marioonetti.firebasefundamentals.ui.ViewEffect
import org.marioonetti.firebasefundamentals.ui.ViewEvent
import org.marioonetti.firebasefundamentals.ui.ViewState

class HomeViewModel(
    private val digimonRepository: DigimonRepository,
) : RootViewModel<HomeState, HomeEvent, HomeEffect>(HomeState.Loading) {

    init {
        fetchDigimons()
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnDigimonClick -> {
                vmScope.launch {
                    setEffect(HomeEffect.NavigateToDetail(event.digimonName))
                }
            }
            is HomeEvent.OnTryAgain -> {
                updateState { HomeState.Loading }
                fetchDigimons()
            }
        }
    }

    private fun fetchDigimons() {
        vmScope.launch {
            digimonRepository.getAllDigimon().fold(
                error = {
                    updateState { HomeState.Error(it) }
                },
                success = { digimonList ->
                    updateState { (HomeState.Idle(digimonList)) }
                }
            )
        }
    }
}


sealed class HomeState: ViewState() {
    data class Idle(
        val digimonList: List<DigimonUi> = emptyList(),
    ): HomeState()
    data class Error(
        val error: AppError,
    ): HomeState()

    data object Loading: HomeState()
}

sealed class HomeEvent : ViewEvent() {
    data class OnDigimonClick(
        val digimonName: String,
    ) : HomeEvent()

    data object OnTryAgain : HomeEvent()
}

sealed class HomeEffect : ViewEffect() {
    data class NavigateToDetail(
        val digimonName: String,
    ) : HomeEffect()
}