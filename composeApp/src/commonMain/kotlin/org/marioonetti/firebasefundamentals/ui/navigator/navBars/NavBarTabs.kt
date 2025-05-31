package org.marioonetti.firebasefundamentals.ui.navigator.navBars

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import org.marioonetti.firebasefundamentals.ui.navigator.Screen

@Composable
fun currentRootSection(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    return if (currentRoute == Screen.Detail.route) {
        val previousRoute = navController.previousBackStackEntry?.destination?.route
        when (previousRoute) {
            Screen.Favourite.route -> Screen.Favourite.route
            else -> Screen.DigimonList.route
        }
    } else {
        currentRoute ?: Screen.DigimonList.route
    }
}