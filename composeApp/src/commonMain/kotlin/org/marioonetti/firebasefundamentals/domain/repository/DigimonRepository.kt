package org.marioonetti.firebasefundamentals.domain.repository

import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonDto
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi

interface DigimonRepository {
    suspend fun getRandomDigimon(): Either<AppError, List<DigimonDto>>

    suspend fun getDigimonByName(name: String): Either<AppError, DigimonUi>
}