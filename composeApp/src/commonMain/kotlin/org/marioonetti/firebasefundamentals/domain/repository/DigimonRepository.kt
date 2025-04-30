package org.marioonetti.firebasefundamentals.domain.repository

import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonDto
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.core.Success
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi

interface DigimonRepository {
    suspend fun getAllDigimon(): Either<AppError, List<DigimonUi>>

    suspend fun getDigimonByName(name: String): Either<AppError, DigimonUi>

    suspend fun saveFavDigimon(digimon: DigimonUi): Either<AppError, Success>

    suspend fun getAllFavDigimonByUser(): Either<AppError, List<DigimonUi>>

    suspend fun checkFavDigimon(name: String): Either<AppError, Boolean>
}