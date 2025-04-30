package org.marioonetti.firebasefundamentals.data.model.digimon

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DigimonEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val level: String,
    val imageUrl: String,
    val description: String,
)