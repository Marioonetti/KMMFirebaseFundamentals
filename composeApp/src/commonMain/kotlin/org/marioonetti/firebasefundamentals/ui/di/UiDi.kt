package org.marioonetti.firebasefundamentals.ui.di

import org.koin.dsl.module
import org.marioonetti.firebasefundamentals.ui.register.RegisterViewModel

val uiModule =
    module {
        factory { RegisterViewModel(get()) }
    }