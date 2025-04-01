package org.marioonetti.firebasefundamentals.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import firebasefundamentals.composeapp.generated.resources.Res
import firebasefundamentals.composeapp.generated.resources.home_top_bar_title
import org.jetbrains.compose.resources.stringResource
import org.marioonetti.firebasefundamentals.ui.shared.CustomTopAppBar

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
) {
    Scaffold(
            bottomBar = { CustomTopAppBar(title = stringResource(Res.string.home_top_bar_title)) }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            Button(
                onClick = { onEvent(HomeEvent.OnLogOut) }
            ) {
                Text("Log out")
            }
        }
    }
}

