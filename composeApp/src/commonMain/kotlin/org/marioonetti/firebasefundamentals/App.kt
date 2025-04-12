package org.marioonetti.firebasefundamentals

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.setSingletonImageLoaderFactory
import org.marioonetti.firebasefundamentals.ui.navigator.Navigator
import org.marioonetti.firebasefundamentals.ui.screens.register.RegisterRoute
import org.marioonetti.firebasefundamentals.utils.MyAppColors.Background
import org.marioonetti.firebasefundamentals.utils.MyAppColors.OnBackground
import org.marioonetti.firebasefundamentals.utils.MyAppColors.OnError
import org.marioonetti.firebasefundamentals.utils.MyAppColors.OnPrimary
import org.marioonetti.firebasefundamentals.utils.MyAppColors.OnSecondary
import org.marioonetti.firebasefundamentals.utils.MyAppColors.OnSurface
import org.marioonetti.firebasefundamentals.utils.MyAppColors.Primary
import org.marioonetti.firebasefundamentals.utils.MyAppColors.Secondary
import org.marioonetti.firebasefundamentals.utils.getAsyncImageLoader

@Composable
fun App() {

    val appColorPalette = lightColorScheme(
        primary = Primary,
        secondary = Secondary,
        background = Background,
        onPrimary = OnPrimary,
        onSecondary = OnSecondary,
        onBackground = OnBackground,
        onSurface = OnSurface,
        onError = OnError
    )

    MaterialTheme(
        colorScheme = appColorPalette
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Navigator()
        }
    }
}