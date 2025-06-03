package org.marioonetti.firebasefundamentals.ui.screens.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi
import org.marioonetti.firebasefundamentals.ui.shared.ErrorComposable
import org.marioonetti.firebasefundamentals.ui.shared.LoadingComposable
import org.marioonetti.firebasefundamentals.ui.theme.AudioWide
import org.marioonetti.firebasefundamentals.utils.MyAppColors
import org.marioonetti.firebasefundamentals.utils.Spacings

@Composable
fun DigimonDetailScreen(
    state: DigimonDetailState,
    onEvent: (DigimonDetailEvent) -> Unit,
) {
    when (state) {
        is DigimonDetailState.Loading -> {
            LoadingComposable()
        }
        is DigimonDetailState.Idle -> {
            DigimonDetailBodyComposable(state, onEvent)
        }
        is DigimonDetailState.Error -> {
            ErrorComposable(state.error, { onEvent(DigimonDetailEvent.OnTryAgain) })
        }
    }
}

@Composable
fun DigimonDetailBodyComposable(
    state: DigimonDetailState.Idle,
    onEvent: (DigimonDetailEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .background(MyAppColors.LightBlue)
            .fillMaxSize()
            .padding(horizontal = Spacings.p16)
            .padding(top = Spacings.p8),
    ) {
        DigimonImageComposable(
            digimonUi = state.digimon,
            isFavourite = state.isFavourite,
            onEvent = onEvent,
            modifier = Modifier.weight(0.5f)
        )
        Spacer(Modifier.height(Spacings.p32))
        DigimonInfoComposable(
            description = state.digimon.description ?: "",
            modifier = Modifier.weight(0.5f)
        )
    }
}

@Composable
fun DigimonImageComposable(
    digimonUi: DigimonUi,
    modifier: Modifier,
    isFavourite: Boolean,
    onEvent: (DigimonDetailEvent) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Card(
            modifier = Modifier.weight(0.9f),
            border = BorderStroke(Spacings.p8, MyAppColors.TopAppBarTitleColor),
            shape = CutCornerShape(Spacings.p16),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                AsyncImage(
                    model = digimonUi.imageUrl,
                    contentDescription = "Digimon Image",
                    onError = { println("Error loading the image $it") },
                    modifier = Modifier.fillMaxWidth()
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(Spacings.p8),
                    onClick = { onEvent(DigimonDetailEvent.OnFavouriteTap) },
                    content = {
                        Icon(
                            imageVector = if (isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favourite",
                            tint = MyAppColors.TopAppBarTitleColor,
                            modifier = Modifier.size(Spacings.p64)
                        )
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(Spacings.p8))
        Text(
            text = digimonUi.name,
            fontFamily = AudioWide(),
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            color = MyAppColors.TopAppBarTitleColor,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(MyAppColors.TopAppBarColor)
                .border(BorderStroke(Spacings.p4, MyAppColors.TopAppBarTitleColor))
                .padding(Spacings.p8)
        )
    }
}


@Composable
fun DigimonInfoComposable(
    description: String,
    modifier: Modifier,
    borderColor: Color = MyAppColors.TopAppBarTitleColor,
) {
    Column(
        modifier = modifier
            .clip(CutCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(MyAppColors.TopAppBarColor)
            .fillMaxHeight()
            .drawBehind {
                val stroke = Spacings.p8.toPx()
                val half = stroke / 2f
                drawLine(
                    color = borderColor,
                    strokeWidth = stroke,
                    start = Offset(half, half),
                    end = Offset(size.width - half, half)
                )
                drawLine(
                    color = borderColor,
                    strokeWidth = stroke,
                    start = Offset(half, half),
                    end = Offset(half, size.height + half )
                )
                drawLine(
                    color = borderColor,
                    strokeWidth = stroke,
                    start = Offset(size.width - half, half),
                    end   = Offset(size.width - half, size.height + half)
                )

            }
            .padding(Spacings.p32),
    ) {
        Text(
            text = description,
            fontFamily = AudioWide(),
            color = MyAppColors.TopAppBarTitleColor,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        )
    }
}