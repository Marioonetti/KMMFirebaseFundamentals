package org.marioonetti.firebasefundamentals.ui.screens.favourite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import org.marioonetti.firebasefundamentals.ui.screens.detail.DigimonDetailViewModel

@Composable
fun FavouriteRoute(
) {
    val viewModel = koinInject<FavouriteViewModel>()
    val state by viewModel.uiState.collectAsState()

    FavouriteScreen(
        state = state,
        onEvent = viewModel::onEvent,
    )
}