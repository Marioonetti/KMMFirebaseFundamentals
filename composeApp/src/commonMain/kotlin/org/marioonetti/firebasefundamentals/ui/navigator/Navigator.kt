package org.marioonetti.firebasefundamentals.ui.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.marioonetti.firebasefundamentals.ui.screens.home.HomeRoute
import org.marioonetti.firebasefundamentals.ui.screens.login.LoginRoute
import org.marioonetti.firebasefundamentals.ui.screens.register.RegisterRoute
import org.marioonetti.firebasefundamentals.ui.screens.splash.SplashRoute

@Composable
fun Navigator() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashRoute(navController)
        }
        composable(route = Screen.Register.route) {
           RegisterRoute(navController)
        }
        composable(route = Screen.Home.route) {
            HomeRoute(navController)
        }
        composable(route = Screen.Login.route) {
            LoginRoute(navController)
        }
    }
}