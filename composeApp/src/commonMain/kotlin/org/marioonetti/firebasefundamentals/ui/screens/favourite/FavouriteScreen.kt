package org.marioonetti.firebasefundamentals.ui.screens.favourite

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import firebasefundamentals.composeapp.generated.resources.Res
import firebasefundamentals.composeapp.generated.resources.fav_background
import org.jetbrains.compose.resources.painterResource
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi
import org.marioonetti.firebasefundamentals.ui.shared.ErrorComposable
import org.marioonetti.firebasefundamentals.ui.shared.LoadingComposable
import kotlin.random.Random

@Composable
fun FavouriteScreen(
    state: FavouriteState,
    onEvent: (FavouriteEvent) -> Unit,
) {
    when (state) {
        is FavouriteState.Loading -> {
            LoadingComposable()
        }
        is FavouriteState.Idle -> {
            FavouriteBodyComposable(state, onEvent)
        }
        is FavouriteState.Error -> {
            ErrorComposable(state.error, onClick = { onEvent(FavouriteEvent.OnTryAgain) } )
        }
    }
}

@Composable
fun FavouriteBodyComposable(
    state: FavouriteState.Idle,
    onEvent: (FavouriteEvent) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(Res.drawable.fav_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 60.dp)
                .padding(top = 100.dp, bottom = 120.dp)
        ) {
            val containerW = maxWidth
            val containerH = maxHeight

            state.digimonList.forEach { digimon ->
                FavDigimonItem(digimon = digimon, containerW, containerH, onEvent)
            }
        }
    }
}

@Composable
fun FavDigimonItem(
    digimon: DigimonUi,
    maxW: Dp, maxH: Dp,
    onEvent: (FavouriteEvent) -> Unit) {
    val size = 64.dp

    val animX = remember {
        Animatable(
            (Random.nextFloat() * (maxW.value - size.value)).dp,
            Dp.VectorConverter
        )
    }
    val animY = remember {
        Animatable(
            (Random.nextFloat() * (maxH.value - size.value)).dp,
            Dp.VectorConverter
        )
    }

    LaunchedEffect(Unit) {
        while (true) {
            val targetX = Random.nextFloat() * (maxW.value - size.value)
            val targetY = Random.nextFloat() * (maxH.value - size.value)
            animX.animateTo(targetX.dp, animationSpec = tween(5000))
            animY.animateTo(targetY.dp, animationSpec = tween(5000))
        }
    }

    AsyncImage(
        model = digimon.imageUrl,
        contentDescription = null,
        modifier = Modifier
            .size(size)
            .graphicsLayer {
                translationX = animX.value.toPx()
                translationY = animY.value.toPx()
            }
            .clickable {
                onEvent(FavouriteEvent.OnFavouriteTap(digimon.name))
            }
    )
}