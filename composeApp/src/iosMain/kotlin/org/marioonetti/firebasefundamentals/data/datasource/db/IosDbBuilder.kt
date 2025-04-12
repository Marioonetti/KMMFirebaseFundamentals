package org.marioonetti.firebasefundamentals.data.datasource.db

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import org.marioonetti.firebasefundamentals.data.datasource.local.db.RoomDb
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

fun iosDatabaseBuilder(): RoomDatabase.Builder<RoomDb> {
    val dbFilePath = documentDirectory() + "/${RoomDb.DB_NAME}"

    return Room.databaseBuilder<RoomDb>(
        dbFilePath
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val directory = NSFileManager.defaultManager().URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )

    return requireNotNull(directory?.path()) {
        "Unable to get document directory"
    }
}