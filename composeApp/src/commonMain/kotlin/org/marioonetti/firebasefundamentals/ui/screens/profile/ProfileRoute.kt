package org.marioonetti.firebasefundamentals.ui.screens.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import org.koin.compose.koinInject
import org.marioonetti.firebasefundamentals.ui.navigator.Screen

@Composable
fun ProfileRoute(
    rootNavHostController: NavHostController,
    homeNavController: NavHostController
) {
    val viewModel = koinInject<ProfileViewModel>()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is ProfileEffect.NavigateToLogin -> {
                    homeNavController.popBackStack(
                        homeNavController.graph.startDestinationId,
                        inclusive = true
                    )
                    rootNavHostController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }
        }
    }

    ProfileScreen(
        state = state,
        onEvent = viewModel::onEvent,
    )
}