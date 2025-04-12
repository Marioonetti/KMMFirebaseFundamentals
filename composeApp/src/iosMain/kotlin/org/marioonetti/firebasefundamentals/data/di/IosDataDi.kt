package org.marioonetti.firebasefundamentals.data.di

import androidx.room.RoomDatabase
import org.koin.dsl.module
import org.marioonetti.firebasefundamentals.data.datasource.db.iosDatabaseBuilder
import org.marioonetti.firebasefundamentals.data.datasource.local.db.RoomDb

val iosDataModule = module {
    single<RoomDatabase.Builder<RoomDb>> { iosDatabaseBuilder() }
}