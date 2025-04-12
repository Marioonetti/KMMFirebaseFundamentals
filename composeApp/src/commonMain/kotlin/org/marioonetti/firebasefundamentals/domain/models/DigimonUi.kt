package org.marioonetti.firebasefundamentals.domain.models

data class DigimonUi(
    val id: Long? = null,
    val name: String,
    val imageUrl: String,
    val level: String,
    val description: String? = null,
)