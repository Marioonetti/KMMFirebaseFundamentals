package org.marioonetti.firebasefundamentals

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.marioonetti.firebasefundamentals.data.di.dataModule
import org.marioonetti.firebasefundamentals.data.di.iosDataModule
import org.marioonetti.firebasefundamentals.domain.di.domainModule
import org.marioonetti.firebasefundamentals.ui.di.uiModule

fun MainViewController() = ComposeUIViewController {
    stopKoin()
    startKoin {
        modules(uiModule, domainModule, dataModule, iosDataModule)
    }
    App()
}