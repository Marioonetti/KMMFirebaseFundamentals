package org.marioonetti.firebasefundamentals.data.model.digimon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DigimonDto(
    @SerialName("img")
    val img: String?,
    @SerialName("level")
    val level: String?,
    @SerialName("name")
    val name: String?,
)