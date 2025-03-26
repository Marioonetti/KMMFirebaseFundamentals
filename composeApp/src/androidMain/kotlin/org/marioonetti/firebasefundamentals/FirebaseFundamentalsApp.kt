package org.marioonetti.firebasefundamentals

import android.app.Application
import com.google.firebase.FirebaseApp
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import org.koin.core.context.startKoin
import org.marioonetti.firebasefundamentals.data.di.dataModule
import org.marioonetti.firebasefundamentals.domain.di.domainModule
import org.marioonetti.firebasefundamentals.ui.di.uiModule

class FirebaseFundamentalsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(uiModule, domainModule, dataModule)
        }
    }
}