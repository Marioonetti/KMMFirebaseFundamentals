package org.marioonetti.firebasefundamentals.ui.screens.home.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonDto
import org.marioonetti.firebasefundamentals.utils.Spacings
import org.marioonetti.firebasefundamentals.utils.getDigimonLevelColor

@Composable
fun DigimonListItemComposable(
    digimon: DigimonDto,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val levelColor = getDigimonLevelColor(digimon.level ?: "")

    Card(
        modifier = modifier,
        border = BorderStroke(Spacings.p8, levelColor),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White,
        ),
        onClick = { onClick(digimon.name ?: "") }
    ) {
        Column(modifier = Modifier.padding(Spacings.p8)) {
            AsyncImage(
                model = digimon.img,
                contentDescription = "Digimon Image",
                onError = { println("Error loading the image $it") },
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
            DigimonListItemInfoComposable(digimon, levelColor)
        }
    }
}

@Composable
fun DigimonListItemInfoComposable(
    digimon: DigimonDto,
    levelColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(Spacings.p4))
        Text(
            text = digimon.name ?: "",
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.size(Spacings.p4))
        Text(
            text = digimon.level ?: "",
            style = MaterialTheme.typography.bodyLarge,
            color = levelColor,
        )
    }
}