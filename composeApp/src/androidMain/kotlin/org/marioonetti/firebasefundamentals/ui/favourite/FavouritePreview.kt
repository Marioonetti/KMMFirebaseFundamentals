package org.marioonetti.firebasefundamentals.ui.favourite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.marioonetti.firebasefundamentals.domain.mappers.toDigimonUi
import org.marioonetti.firebasefundamentals.domain.models.mock.mockDigimonList
import org.marioonetti.firebasefundamentals.ui.screens.favourite.FavouriteScreen
import org.marioonetti.firebasefundamentals.ui.screens.favourite.FavouriteState
import org.marioonetti.firebasefundamentals.utils.MyAppColors

@Preview
@Composable
fun FavouritePreview() {
    Box(
        modifier = Modifier.background(MyAppColors.LightBlue).fillMaxSize()
    ) {
        FavouriteScreen(
            state = FavouriteState.Idle(
                digimonList = mockDigimonList.map { it.toDigimonUi() }
            ),
            onEvent = {}
        )
    }
}