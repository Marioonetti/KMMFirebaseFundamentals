package org.marioonetti.firebasefundamentals.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonEntity

@Dao
interface DigimonDao {

    @Insert
    suspend fun insert(digimon: DigimonEntity): Long

    @Delete
    suspend fun delete(digimon: DigimonEntity): Int

    @Query("SELECT * FROM DigimonEntity WHERE name = :name")
    suspend fun getDigimonByName(name: String): DigimonEntity?
}