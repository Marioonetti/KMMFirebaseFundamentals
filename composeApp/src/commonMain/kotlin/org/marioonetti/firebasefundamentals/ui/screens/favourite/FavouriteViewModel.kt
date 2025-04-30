package org.marioonetti.firebasefundamentals.ui.screens.favourite

import kotlinx.coroutines.launch
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi
import org.marioonetti.firebasefundamentals.domain.repository.DigimonRepository
import org.marioonetti.firebasefundamentals.ui.RootViewModel
import org.marioonetti.firebasefundamentals.ui.ViewEffect
import org.marioonetti.firebasefundamentals.ui.ViewEvent
import org.marioonetti.firebasefundamentals.ui.ViewState

class FavouriteViewModel(
    private val digimonRepository: DigimonRepository,
): RootViewModel<FavouriteState, FavouriteEvent, FavouriteViewEffect>(FavouriteState.Loading) {

    init {
        loadFavourites()
    }

    override fun onEvent(event: FavouriteEvent) {
        when (event) {
            is FavouriteEvent.OnFavouriteTap -> {
                // Handle click event
            }
        }
    }

    private fun loadFavourites() {
        vmScope.launch {
            digimonRepository.getAllFavDigimonByUser().fold(
                success = { digimonList ->
                    updateState {
                        FavouriteState.Idle(digimonList)
                    }
                },
                error = {
                    println("Error loading favourites $it")
                }
            )
        }
    }
}

sealed class FavouriteEvent: ViewEvent() {
    data object OnFavouriteTap : FavouriteEvent()
}

sealed class FavouriteState: ViewState() {
    data class Idle(
        val digimonList: List<DigimonUi>
    ) : FavouriteState()
    data object Loading : FavouriteState()
}

sealed class FavouriteViewEffect: ViewEffect() {
    data object ShowDetail : FavouriteViewEffect()
}