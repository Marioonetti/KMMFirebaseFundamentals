package org.marioonetti.firebasefundamentals.ui.navigator

sealed class Screen(
    val route: String,
) {
    data object Home : Screen("/home")

    data object Login : Screen("/login")

    data object Register : Screen("/register")

    data object Splash : Screen("/splash")

    fun withArgs(vararg args: Pair<String, String>): String {
        // replace all keys with values
        var newRoute = route
        args.forEach { (key, value) ->
            newRoute = newRoute.replace("{$key}", value)
        }
        return newRoute
    }
}