package org.marioonetti.firebasefundamentals.ui.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.koinInject

@Composable
fun RegisterRoute() {
    val viewModel = koinInject<RegisterViewModel>()
    val state by viewModel.uiState.collectAsState()

    RegisterScreen(state = state, onEvent = viewModel::onEvent)
}