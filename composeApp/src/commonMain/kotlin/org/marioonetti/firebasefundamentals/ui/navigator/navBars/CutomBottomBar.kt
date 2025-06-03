package org.marioonetti.firebasefundamentals.ui.navigator.navBars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.marioonetti.firebasefundamentals.ui.navigator.Screen
import org.marioonetti.firebasefundamentals.utils.MyAppColors

@Composable
fun CustomBottomBar(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
) {

    val homeIcon = if (selectedTab == Screen.DigimonList.route || selectedTab == Screen.Detail.route) {
        Icons.Filled.Home
    } else {
        Icons.Outlined.Home
    }

    val favIcon = if (selectedTab == Screen.Favourite.route) {
        Icons.Filled.Favorite
    } else {
        Icons.Outlined.FavoriteBorder
    }

    BottomAppBar(
        modifier = Modifier.height(56.dp),
        containerColor = MyAppColors.TopAppBarColor
    ) {
        Row(
            modifier = Modifier.fillMaxSize().height(56.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = homeIcon,
                contentDescription = null,
                tint = MyAppColors.TopAppBarTitleColor,
                modifier = Modifier.clickable {
                    onTabSelected(Screen.DigimonList.route)
                }.weight(0.5f).fillMaxSize(),
            )
            Icon(
                imageVector = favIcon,
                contentDescription = null,
                tint = MyAppColors.TopAppBarTitleColor,
                modifier = Modifier.clickable {
                    onTabSelected(Screen.Favourite.route)
                }.weight(0.5f).fillMaxSize(),
            )
        }
    }
}