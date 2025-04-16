package org.marioonetti.firebasefundamentals.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil3.compose.AsyncImage
import org.marioonetti.firebasefundamentals.utils.Spacings
import org.marioonetti.firebasefundamentals.utils.getDigimonLevelColor

@Composable
fun DigimonDetailScreen(
    state: DigimonDetailState,
    onEvent: (DigimonDetailEvent) -> Unit,
) {
    when (state) {
        is DigimonDetailState.Loading -> {
            Column { }
        }
        is DigimonDetailState.Idle -> {
            DigimonDetailBodyComposable(state, onEvent)
        }
    }
}

@Composable
fun DigimonDetailBodyComposable(
    state: DigimonDetailState.Idle,
    onEvent: (DigimonDetailEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(Spacings.p16)
            .border(Spacings.p8, color = getDigimonLevelColor(state.digimon.level ?: ""))
            .padding(Spacings.p8),
    ) {
        AsyncImage(
            model = state.digimon.imageUrl,
            contentDescription = "Digimon Image",
            onError = { println("Error loading the image $it") },
            modifier = Modifier.fillMaxWidth().weight(0.5f)
        )
        Column(modifier = Modifier.weight(0.5f)) {
            Text("Name: ${state.digimon.name}")
            Spacer(modifier = Modifier.height(Spacings.p8))
            Text("Description: \n${state.digimon.description}")
        }

    }
}