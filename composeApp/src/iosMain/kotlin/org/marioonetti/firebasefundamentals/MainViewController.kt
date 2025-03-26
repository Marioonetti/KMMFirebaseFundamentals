package org.marioonetti.firebasefundamentals

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.marioonetti.firebasefundamentals.ui.di.uiModule

fun MainViewController() = ComposeUIViewController {
    stopKoin()
    startKoin {
        modules(uiModule)
    }
    App()
}