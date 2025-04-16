package org.marioonetti.firebasefundamentals.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import firebasefundamentals.composeapp.generated.resources.Res
import firebasefundamentals.composeapp.generated.resources.audio_wide
import firebasefundamentals.composeapp.generated.resources.bungee_spice_regular
import org.jetbrains.compose.resources.Font

@Composable
fun Bungee() = FontFamily(
    Font(Res.font.bungee_spice_regular, weight = FontWeight.Normal),
)

@Composable
fun AudioWide() = FontFamily(
    Font(Res.font.audio_wide, weight = FontWeight.Normal),
)
