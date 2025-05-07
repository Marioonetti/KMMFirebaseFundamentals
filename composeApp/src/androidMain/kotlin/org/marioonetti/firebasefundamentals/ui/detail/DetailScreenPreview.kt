package org.marioonetti.firebasefundamentals.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi
import org.marioonetti.firebasefundamentals.domain.models.mock.mockDigimonList
import org.marioonetti.firebasefundamentals.ui.screens.detail.DigimonDetailBodyComposable
import org.marioonetti.firebasefundamentals.ui.screens.detail.DigimonDetailState
import org.marioonetti.firebasefundamentals.utils.MyAppColors

@Preview
@Composable
fun DetailScreenPreview() {
    Box(
        modifier = Modifier.background(MyAppColors.BackGroundAppColor).fillMaxSize()
    )
    DigimonDetailBodyComposable(
        state = DigimonDetailState.Idle(
            digimon = DigimonUi(
                name = "Agumon",
                imageUrl = mockDigimonList[0].img!!,
                level = "Rookie",
                description = "Agumon is a reptile-like Digimon that resembles a small dinosaur.",
            )
        ),
        onEvent = {}
    )
}