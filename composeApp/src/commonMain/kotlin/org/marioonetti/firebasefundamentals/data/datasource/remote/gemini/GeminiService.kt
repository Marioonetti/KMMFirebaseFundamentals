package org.marioonetti.firebasefundamentals.data.datasource.remote.gemini

import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either

interface GeminiService {
    suspend fun generateContent(prompt: String): Either<AppError, String>
}