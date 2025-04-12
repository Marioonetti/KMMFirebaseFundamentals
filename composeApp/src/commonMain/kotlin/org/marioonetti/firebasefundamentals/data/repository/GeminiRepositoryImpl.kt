package org.marioonetti.firebasefundamentals.data.repository

import org.marioonetti.firebasefundamentals.data.datasource.remote.gemini.GeminiService
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.repository.GeminiRepository

class GeminiRepositoryImpl(
    private val geminiService: GeminiService
): GeminiRepository {
    override suspend fun generateMessage(prompt: String): Either<AppError, String> = geminiService.generateContent(prompt)
}