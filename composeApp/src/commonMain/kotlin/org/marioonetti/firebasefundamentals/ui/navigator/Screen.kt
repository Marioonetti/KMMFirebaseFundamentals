package org.marioonetti.firebasefundamentals.ui.navigator

import androidx.navigation.NavController
import kotlinx.serialization.Serializable

sealed class Screen(
    val route: String,
) {
    data object Home : Screen("/home")

    data object DigimonList : Screen("/digimonList")

    data object Detail : Screen("/detail/{name}")

    data object Login : Screen("/login")

    data object Register : Screen("/register")

    data object Favourite : Screen("/favourite")

    data object Splash : Screen("/splash")

    data object Profile : Screen("/profile")

    fun withArgs(vararg args: Pair<String, String>): String {
        // replace all keys with values
        var newRoute = route
        args.forEach { (key, value) ->
            newRoute = newRoute.replace("{$key}", value)
        }
        return newRoute
    }
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
