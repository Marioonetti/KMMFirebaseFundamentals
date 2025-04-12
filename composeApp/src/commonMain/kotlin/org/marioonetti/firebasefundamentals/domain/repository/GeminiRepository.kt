package org.marioonetti.firebasefundamentals.domain.repository

import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either

interface GeminiRepository {
    suspend fun generateMessage(prompt: String): Either<AppError, String>
}