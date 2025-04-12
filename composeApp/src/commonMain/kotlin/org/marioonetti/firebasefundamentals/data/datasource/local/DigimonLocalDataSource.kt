package org.marioonetti.firebasefundamentals.data.datasource.local

import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonEntity
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either

interface DigimonLocalDataSource {
    suspend fun getDigimonByName(name: String): Either<AppError, DigimonEntity?>

    suspend fun saveDigimon(digimon: DigimonEntity): Either<AppError, Unit>
}