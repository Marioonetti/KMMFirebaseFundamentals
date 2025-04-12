package org.marioonetti.firebasefundamentals.ui.navigator

import androidx.navigation.NavController
import kotlinx.serialization.Serializable

sealed class Screen(
    val route: String,
) {
    data object Home : Screen("/home")

    data object Detail : Screen("/detail")

    data object Login : Screen("/login")

    data object Register : Screen("/register")

    data object Splash : Screen("/splash")
}

@Serializable
data class DigimonDetailArgs(val name: String)

fun NavController.navigateClearingBackStack(route: String) {
    this.navigate(route) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}
