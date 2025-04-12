package org.marioonetti.firebasefundamentals.data.di

import androidx.room.RoomDatabase
import org.koin.dsl.module
import org.marioonetti.firebasefundamentals.data.datasource.db.androidDbBuilder
import org.marioonetti.firebasefundamentals.data.datasource.local.db.RoomDb

val androidDataModule = module {
    single<RoomDatabase.Builder<RoomDb>> { androidDbBuilder(get()) }
}