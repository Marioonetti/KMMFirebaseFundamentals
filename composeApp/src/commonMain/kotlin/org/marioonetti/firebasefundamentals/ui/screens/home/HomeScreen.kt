package org.marioonetti.firebasefundamentals.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.marioonetti.firebasefundamentals.ui.screens.home.composables.DigimonListItemComposable
import org.marioonetti.firebasefundamentals.ui.shared.ErrorComposable
import org.marioonetti.firebasefundamentals.ui.shared.LoadingComposable
import org.marioonetti.firebasefundamentals.utils.Spacings

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is HomeState.Loading -> {
            LoadingComposable()
        }
        is HomeState.Idle -> {
            HomeBodyComposable(state, onEvent, modifier)
        }
        is HomeState.Error -> {
            ErrorComposable(
                error = state.error,
                onClick = { onEvent(HomeEvent.OnTryAgain) },
            )
        }
    }
}

@Composable
fun HomeBodyComposable(
    state: HomeState.Idle,
    onEvent: (HomeEvent) -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = Spacings.p32, vertical = Spacings.p8),
        ) {
            state.digimonList.forEach { digimon ->
                item {
                    DigimonListItemComposable(
                        digimon = digimon,
                        onClick = { digimonName ->
                            onEvent(HomeEvent.OnDigimonClick(digimonName))
                        },
                    )
                    Spacer(modifier = Modifier.size(Spacings.p8))
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }

}
