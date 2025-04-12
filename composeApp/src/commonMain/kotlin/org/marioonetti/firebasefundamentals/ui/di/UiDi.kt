package org.marioonetti.firebasefundamentals.ui.di

import org.koin.dsl.module
import org.marioonetti.firebasefundamentals.ui.screens.detail.DigimonDetailViewModel
import org.marioonetti.firebasefundamentals.ui.screens.home.HomeViewModel
import org.marioonetti.firebasefundamentals.ui.screens.login.LoginViewModel
import org.marioonetti.firebasefundamentals.ui.screens.register.RegisterViewModel
import org.marioonetti.firebasefundamentals.ui.screens.splash.SplashViewModel

val uiModule =
    module {
        factory { RegisterViewModel(get()) }
        factory { LoginViewModel(get()) }
        factory { HomeViewModel(get(), get()) }
        factory { SplashViewModel(get()) }
        factory { DigimonDetailViewModel(get(), get(), get()) }
    }