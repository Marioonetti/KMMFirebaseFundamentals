package org.marioonetti.firebasefundamentals.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import firebasefundamentals.composeapp.generated.resources.Res
import firebasefundamentals.composeapp.generated.resources.home_top_bar_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.marioonetti.firebasefundamentals.ui.navigator.Screen
import org.marioonetti.firebasefundamentals.ui.navigator.navBars.CustomBottomBar
import org.marioonetti.firebasefundamentals.ui.navigator.navBars.CustomTopAppBar
import org.marioonetti.firebasefundamentals.ui.navigator.navBars.currentRoute

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