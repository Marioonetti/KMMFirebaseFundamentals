package org.marioonetti.firebasefundamentals.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonDto
import org.marioonetti.firebasefundamentals.domain.models.mock.mockDigimonList
import org.marioonetti.firebasefundamentals.ui.screens.home.HomeScreen
import org.marioonetti.firebasefundamentals.ui.screens.home.HomeState
import org.marioonetti.firebasefundamentals.ui.screens.home.composables.DigimonListItemComposable

@Preview
@Composable
fun DigimonListItemPreview() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        DigimonListItemComposable(
            digimon = mockDigimonList[0],
            {}
        )
        Spacer(modifier = Modifier.height(10.dp))
        DigimonListItemComposable(
            digimon = mockDigimonList[1],
            {}
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen(HomeState.Idle(mockDigimonList), onEvent = {})
}