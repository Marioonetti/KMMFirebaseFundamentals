package org.marioonetti.firebasefundamentals.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import org.koin.compose.koinInject
import org.marioonetti.firebasefundamentals.ui.navigator.Screen

@Composable
fun HomeRoute(
    homeNavController: NavHostController,
) {
    val viewModel = koinInject<HomeViewModel>()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is HomeEffect.NavigateToDetail -> {
                    homeNavController.navigate(Screen.Detail.withArgs("name" to sideEffect.digimonName))
                }
            }
        }
    }

    HomeScreen(
        state = state,
        onEvent = viewModel::onEvent,
    )
}