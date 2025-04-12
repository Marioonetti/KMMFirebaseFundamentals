package org.marioonetti.firebasefundamentals.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import firebasefundamentals.composeapp.generated.resources.Res
import firebasefundamentals.composeapp.generated.resources.home_top_bar_title
import org.jetbrains.compose.resources.stringResource
import org.marioonetti.firebasefundamentals.ui.screens.home.composables.DigimonListItemComposable
import org.marioonetti.firebasefundamentals.ui.shared.CustomTopAppBar
import org.marioonetti.firebasefundamentals.utils.Spacings

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
) {

    when (state) {
        is HomeState.Loading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is HomeState.Idle -> {
            HomeBodyComposable(state, onEvent)
        }
    }
}

@Composable
fun HomeBodyComposable(
    state: HomeState.Idle,
    onEvent: (HomeEvent) -> Unit
) {
    Scaffold(
        topBar = { CustomTopAppBar(title = stringResource(Res.string.home_top_bar_title)) }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(Spacings.p8),
            ) {
                state.digimonList.forEach { digimon ->
                    item {
                        DigimonListItemComposable(
                            digimon = digimon,
                            onClick = { digimonName ->
                                onEvent(HomeEvent.OnDigimonClick(digimonName))
                            },
                            modifier = Modifier.fillMaxSize().height(300.dp)
                        )
                        Spacer(modifier = Modifier.size(Spacings.p8))
                    }
                }
            }
        }
    }

}
