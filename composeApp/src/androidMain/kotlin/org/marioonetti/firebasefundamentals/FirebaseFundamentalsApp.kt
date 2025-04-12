package org.marioonetti.firebasefundamentals

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.marioonetti.firebasefundamentals.data.di.androidDataModule
import org.marioonetti.firebasefundamentals.data.di.dataModule
import org.marioonetti.firebasefundamentals.domain.di.domainModule
import org.marioonetti.firebasefundamentals.ui.di.uiModule

class FirebaseFundamentalsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FirebaseFundamentalsApp)
            modules(uiModule, domainModule, dataModule, androidDataModule)
        }
    }
}