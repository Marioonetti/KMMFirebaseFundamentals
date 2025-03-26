package org.marioonetti.firebasefundamentals.domain.repository

import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.core.Success

interface UserRepository {

    suspend fun register(email: String, password: String): Either<AppError, Success>

}