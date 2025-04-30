package org.marioonetti.firebasefundamentals.data.datasource.remote.digimon

import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonDto
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi

interface DigimonRemoteDataSource {

    suspend fun getAllDigimon(): Either<AppError, List<DigimonUi>>

    suspend fun getDigimonByName(name: String): Either<AppError, DigimonDto>

}