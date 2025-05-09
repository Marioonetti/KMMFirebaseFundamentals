package org.marioonetti.firebasefundamentals

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.marioonetti.firebasefundamentals.ui.navigator.RootNavigator
import org.marioonetti.firebasefundamentals.utils.MyAppColors
@Composable
fun App() {
    val appColorPalette = lightColorScheme(
       background = MyAppColors.LightBlue
    )

    MaterialTheme(
        colorScheme = appColorPalette
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            RootNavigator()
        }
    }
}