package org.marioonetti.firebasefundamentals.ui.screens.favourite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import org.marioonetti.firebasefundamentals.ui.navigator.Screen
import org.marioonetti.firebasefundamentals.ui.screens.detail.DigimonDetailViewModel
import org.marioonetti.firebasefundamentals.ui.screens.home.HomeEffect

@Composable
fun FavouriteRoute(
    homeNavController: NavHostController
) {
    val viewModel = koinInject<FavouriteViewModel>()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is FavouriteViewEffect.ShowDetail -> {
                    homeNavController.navigate(Screen.Detail.withArgs("name" to sideEffect.name))
                }
            }
        }
    }

    FavouriteScreen(
        state = state,
        onEvent = viewModel::onEvent,
    )
}