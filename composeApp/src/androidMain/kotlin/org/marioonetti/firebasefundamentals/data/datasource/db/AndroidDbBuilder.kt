package org.marioonetti.firebasefundamentals.data.datasource.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.marioonetti.firebasefundamentals.data.datasource.local.db.RoomDb

fun androidDbBuilder(context: Context): RoomDatabase.Builder<RoomDb> {
    val dbFile = context.applicationContext.getDatabasePath(RoomDb.DB_NAME)
    return Room.databaseBuilder(
        context,
        dbFile.absolutePath
    )
}