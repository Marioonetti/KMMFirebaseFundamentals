package org.marioonetti.firebasefundamentals.utils

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getDigimonLevelColor(level: String): Color {
    val infiniteTransition = rememberInfiniteTransition(label = "GlowAnimation")

    val animatedAlpha = infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "GlowingAlpha"
    )

    return when (level) {
        "Fresh" -> Color(0xFFA0A0A0)
        "In Training", "Training" -> Color(0xFF8BC34A)
        "Rookie" -> Color(0xFF03A9F4)
        "Champion" -> Color(0xFFFFEB3B)
        "Ultimate" -> Color(0xFFFF9800).copy(alpha = animatedAlpha.value)
        "Mega" -> Color(0xFFF44336).copy(alpha = animatedAlpha.value)
        "Armor" -> Color(0xFF9C27B0).copy(alpha = animatedAlpha.value)
        else -> Color.Black
    }
}