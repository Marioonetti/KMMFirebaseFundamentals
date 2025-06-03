package org.marioonetti.firebasefundamentals.ui.navigator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import firebasefundamentals.composeapp.generated.resources.Res
import firebasefundamentals.composeapp.generated.resources.home_top_bar_title
import org.jetbrains.compose.resources.stringResource
import org.marioonetti.firebasefundamentals.ui.navigator.navBars.CustomBottomBar
import org.marioonetti.firebasefundamentals.ui.navigator.navBars.CustomTopAppBar
import org.marioonetti.firebasefundamentals.ui.navigator.navBars.currentRootSection
import org.marioonetti.firebasefundamentals.ui.screens.detail.DigimonDetailRoute
import org.marioonetti.firebasefundamentals.ui.screens.favourite.FavouriteRoute
import org.marioonetti.firebasefundamentals.ui.screens.home.HomeRoute
import org.marioonetti.firebasefundamentals.ui.screens.profile.ProfileRoute

@Composable
fun HomeNavigator(
    rootNavController: NavHostController
) {
    val homeNavController = rememberNavController()

    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(Res.string.home_top_bar_title),
                showBackArrow = currentRoute != Screen.DigimonList.route && currentRoute != Screen.Favourite.route,
                onProfileClick = {
                    homeNavController.navigate(Screen.Profile.route)
                },
                onBackClick = {
                    if (homeNavController.previousBackStackEntry != null) {
                        homeNavController.popBackStack()
                    }
                }
            )
        },
        bottomBar = {
            CustomBottomBar(
                selectedTab = currentRootSection(homeNavController) ?: Screen.Home.route,
                onTabSelected = { route ->
                    if (route != homeNavController.currentDestination?.route) {
                        homeNavController.navigate(route) {
                            popUpTo(homeNavController.graph.startDestinationRoute ?: "") {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    ) { padding ->
        NavHost(
            navController = homeNavController,
            startDestination = Screen.DigimonList.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("name") {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val digimonName: DigimonDetailArgs = backStackEntry.toRoute()
                DigimonDetailRoute(digimonName.name)
            }
            composable(route = Screen.DigimonList.route) {
                HomeRoute(homeNavController)
            }
            composable(route = Screen.Favourite.route) {
                FavouriteRoute(homeNavController)
            }
            composable(route = Screen.Profile.route) {
                ProfileRoute(rootNavController, homeNavController)
            }
        }
    }
}