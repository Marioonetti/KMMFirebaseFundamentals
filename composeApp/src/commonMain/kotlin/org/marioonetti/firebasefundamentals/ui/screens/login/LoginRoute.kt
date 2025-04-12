package org.marioonetti.firebasefundamentals.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import org.koin.compose.koinInject
import org.marioonetti.firebasefundamentals.ui.navigator.Screen
import org.marioonetti.firebasefundamentals.ui.navigator.navigateClearingBackStack
import org.marioonetti.firebasefundamentals.ui.screens.register.RegisterEffect
import org.marioonetti.firebasefundamentals.ui.screens.register.RegisterViewModel

@Composable
fun LoginRoute(
    navController: NavHostController
) {
    val viewModel = koinInject<LoginViewModel>()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is LoginEffect.OnNavigateToHome -> navController.navigateClearingBackStack(Screen.Home.route)
                is LoginEffect.OnNavigateToRegister -> navController.navigate(Screen.Register.route)
            }
        }
    }

    LoginScreen(state = state, onEvent = viewModel::onEvent)
}