package org.marioonetti.firebasefundamentals.ui.screens.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import org.koin.compose.koinInject
import org.marioonetti.firebasefundamentals.ui.navigator.Screen
import org.marioonetti.firebasefundamentals.ui.navigator.navigateClearingBackStack

@Composable
fun RegisterRoute(navController: NavHostController) {
    val viewModel = koinInject<RegisterViewModel>()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is RegisterEffect.OnNavigateToHome -> navController.navigateClearingBackStack(Screen.Home.route)
                is RegisterEffect.OnNavigateToLogin -> navController.popBackStack()
            }
        }
    }

    RegisterScreen(state = state, onEvent = viewModel::onEvent)
}