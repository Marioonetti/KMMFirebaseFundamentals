package org.marioonetti.firebasefundamentals.ui.screens.favourite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.marioonetti.firebasefundamentals.ui.screens.home.composables.DigimonListItemComposable
import org.marioonetti.firebasefundamentals.ui.shared.LoadingComposable

@Composable
fun FavouriteScreen(
    state: FavouriteState,
    onEvent: (FavouriteEvent) -> Unit,
) {
    when (state) {
        is FavouriteState.Loading -> {
            LoadingComposable()
        }
        is FavouriteState.Idle -> {
            FavouriteBodyComposable(state, onEvent)
        }
    }
}

@Composable
fun FavouriteBodyComposable(
    state: FavouriteState.Idle,
    onEvent: (FavouriteEvent) -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.digimonList) { digimon ->
            DigimonListItemComposable(digimon = digimon, onClick = {},)
        }
    }
}