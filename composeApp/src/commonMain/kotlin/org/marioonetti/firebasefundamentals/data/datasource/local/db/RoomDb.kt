package org.marioonetti.firebasefundamentals.data.datasource.local.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import org.marioonetti.firebasefundamentals.data.datasource.local.dao.DigimonDao
import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonEntity

@Database(
    entities = [
        DigimonEntity::class
    ],
    version = 1,
    exportSchema = true
)
@ConstructedBy(AppDatabaseConstrusctor::class)
abstract class RoomDb: RoomDatabase() {
    abstract fun digimonDao(): DigimonDao

    companion object {
        const val DB_NAME = "firebase_fundamentals_db"
    }
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstrusctor: RoomDatabaseConstructor<RoomDb> {
    override fun initialize(): RoomDb
}