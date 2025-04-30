package org.marioonetti.firebasefundamentals.ui.screens.home.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonDto
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi
import org.marioonetti.firebasefundamentals.ui.theme.AudioWide
import org.marioonetti.firebasefundamentals.ui.theme.Bungee
import org.marioonetti.firebasefundamentals.utils.MyAppColors
import org.marioonetti.firebasefundamentals.utils.Spacings
import org.marioonetti.firebasefundamentals.utils.getDigimonLevelColor

@Composable
fun DigimonListItemComposable(
    digimon: DigimonUi,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val levelColor = getDigimonLevelColor(digimon.level)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = Spacings.p128),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(Spacings.p8, MyAppColors.DarkBlueBase),
        shape = CutCornerShape(Spacings.p16),
        onClick = { onClick(digimon.name) }
    ) {
        Row(
            modifier = Modifier.padding(Spacings.p8),
            horizontalArrangement = Arrangement.Start,
        ) {
            AsyncImage(
                model = digimon.imageUrl,
                contentDescription = "Digimon Image",
                onError = { println("Error loading the image $it") },
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.FillHeight,
            )
            Spacer(modifier = Modifier.width(Spacings.p8))
            DigimonListItemInfoComposable(digimon, levelColor)
        }
    }
}

@Composable
fun DigimonListItemInfoComposable(
    digimon: DigimonUi,
    levelColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.size(Spacings.p8))
        Text(
            text = digimon.name,
            fontFamily = Bungee(),
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
        )
        Text(
            text = digimon.level,
            color = levelColor,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontFamily = AudioWide(),
        )
    }
}