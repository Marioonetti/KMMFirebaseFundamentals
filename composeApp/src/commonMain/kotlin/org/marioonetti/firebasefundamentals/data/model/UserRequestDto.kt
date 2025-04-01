package org.marioonetti.firebasefundamentals.data.model

data class UserRequestDto(
    val email: String,
    val username: String = "",
    val password: String
)