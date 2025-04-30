package org.marioonetti.firebasefundamentals.data.model.digimon

import kotlinx.serialization.Serializable

@Serializable
data class DigimonFirebaseDto(
    val name: String,
    val imageUrl: String,
    val level: String,
    val description: String,
)