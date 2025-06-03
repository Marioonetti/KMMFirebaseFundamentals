package org.marioonetti.firebasefundamentals.ui.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun DigimonDetailRoute(
    digimonName: String,
) {
    val viewModel = koinInject<DigimonDetailViewModel>(parameters = { parametersOf(digimonName) })
    val state by viewModel.uiState.collectAsState()

    DigimonDetailScreen(
        state = state,
        onEvent = viewModel::onEvent,
    )
}