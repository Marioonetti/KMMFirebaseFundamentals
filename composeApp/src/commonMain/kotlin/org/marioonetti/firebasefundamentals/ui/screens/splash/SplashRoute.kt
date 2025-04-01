package org.marioonetti.firebasefundamentals.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import org.koin.compose.koinInject
import org.marioonetti.firebasefundamentals.ui.navigator.Screen
import org.marioonetti.firebasefundamentals.ui.screens.register.RegisterEffect
import org.marioonetti.firebasefundamentals.ui.screens.register.RegisterViewModel

@Composable
fun SplashRoute(
    navController: NavHostController
) {
    val viewModel = koinInject<SplashViewModel>()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SplashEffect.NavigateToHome -> navController.navigate(Screen.Home.route)
                is SplashEffect.NavigateToLogin -> navController.navigate(Screen.Login.route)
            }
        }
    }

    SplashScreen(state = state, onEvent = viewModel::onEvent)
}