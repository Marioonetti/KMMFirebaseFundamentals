package org.marioonetti.firebasefundamentals.data.model.firebase

import kotlinx.serialization.Serializable

@Serializable
data class FireBaseUserDto(
    val email: String,
    val username: String,
)