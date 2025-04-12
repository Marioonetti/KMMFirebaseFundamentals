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
                    updateState {
                        DigimonDetailState.Idle(digimon)
                    }
                },
                error =  {
                    println("Error loading digimon $it")
                }
            )
        }
    }

    override fun onEvent(event: DigimonDetailEvent) {
        TODO("Not yet implemented")
    }

}

sealed class DigimonDetailState: ViewState() {
    data object Loading: DigimonDetailState()
    data class Idle(
        val digimon: DigimonUi,
    ): DigimonDetailState()
}

sealed class DigimonDetailEvent: ViewEvent() {

}

sealed class DigimonDetailViewEffect: ViewEffect() {
}
