package org.marioonetti.firebasefundamentals.data.datasource.remote.digimon

import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonDto
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either

interface DigimonRemoteDataSource {

    suspend fun getRandomDigimon(): Either<AppError, List<DigimonDto>>

    suspend fun getDigimonByName(name: String): Either<AppError, DigimonDto>

}