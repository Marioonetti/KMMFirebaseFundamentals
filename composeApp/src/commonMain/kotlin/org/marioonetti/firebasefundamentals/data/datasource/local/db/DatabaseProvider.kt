package org.marioonetti.firebasefundamentals.data.datasource.local.db

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class DatabaseProvider(
    private val builder: RoomDatabase.Builder<RoomDb>,
) {

    fun getDatabase(): RoomDb {
        return builder
            .fallbackToDestructiveMigration(dropAllTables = true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}