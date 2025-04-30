package org.marioonetti.firebasefundamentals.ui.screens.detail

import kotlinx.coroutines.launch
import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonDto
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi
import org.marioonetti.firebasefundamentals.domain.repository.DigimonRepository
import org.marioonetti.firebasefundamentals.domain.repository.GeminiRepository
import org.marioonetti.firebasefundamentals.ui.RootViewModel
import org.marioonetti.firebasefundamentals.ui.ViewEffect
import org.marioonetti.firebasefundamentals.ui.ViewEvent
import org.marioonetti.firebasefundamentals.ui.ViewState

class DigimonDetailViewModel(
    private val digimonName: String,
    private val digimonRepository: DigimonRepository,
    private val geminiRepository: GeminiRepository,
): RootViewModel<DigimonDetailState, DigimonDetailEvent, DigimonDetailViewEffect>(DigimonDetailState.Loading) {

    init {
        vmScope.launch {
            digimonRepository.getDigimonByName(digimonName).fold(
                success = { digimon ->
                    checkIfDigimonIsFavourite(digimon)
                },
                error =  {
                    println("Error loading digimon $it")
                }
            )
        }
    }

    override fun onEvent(event: DigimonDetailEvent) {
        when (event) {
            is DigimonDetailEvent.OnFavouriteTap -> {
                if (uiState.value is DigimonDetailState.Idle) {
                    vmScope.launch {
                        digimonRepository.saveFavDigimon((uiState.value as DigimonDetailState.Idle).digimon).fold(
                            success = {
                                updateState {
                                    val currentState = uiState.value as DigimonDetailState.Idle
                                    currentState.copy(isFavourite = !currentState.isFavourite)
                                }
                                println("Digimon saved as favourite")
                            },
                            error = {
                                println("Error saving digimon as favourite $it")
                            }
                        )
                    }
                }
            }
        }
    }

    private fun checkIfDigimonIsFavourite(digimon: DigimonUi) {
        vmScope.launch {
            digimonRepository.checkFavDigimon(digimon.name).fold(
                success = { isFavourite ->
                    updateState {
                        DigimonDetailState.Idle(digimon, isFavourite)
                    }
                },
                error = {
                    println("Error checking if digimon is favourite $it")
                }
            )
        }
    }

}

sealed class DigimonDetailState: ViewState() {
    data object Loading: DigimonDetailState()
    data class Idle(
        val digimon: DigimonUi,
        val isFavourite: Boolean = false,
    ): DigimonDetailState()
}

sealed class DigimonDetailEvent: ViewEvent() {
    data object OnFavouriteTap: DigimonDetailEvent()
}

sealed class DigimonDetailViewEffect: ViewEffect() {
}
