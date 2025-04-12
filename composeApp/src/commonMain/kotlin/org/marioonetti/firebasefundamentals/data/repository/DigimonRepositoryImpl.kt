package org.marioonetti.firebasefundamentals.data.repository

import org.marioonetti.firebasefundamentals.data.datasource.local.DigimonLocalDataSource
import org.marioonetti.firebasefundamentals.data.datasource.remote.digimon.DigimonRemoteDataSource
import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonEntity
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.mappers.toDigimonUi
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi
import org.marioonetti.firebasefundamentals.domain.repository.DigimonRepository
import org.marioonetti.firebasefundamentals.domain.repository.GeminiRepository

class DigimonRepositoryImpl(
    private val digimonRemoteDataSource: DigimonRemoteDataSource,
    private val digimonLocalDataSource: DigimonLocalDataSource,
    private val geminiRepository: GeminiRepository,
): DigimonRepository {
    override suspend fun getRandomDigimon() = digimonRemoteDataSource.getRandomDigimon()

    override suspend fun getDigimonByName(name: String): Either<AppError, DigimonUi> {

        return when (val localDigimon = digimonLocalDataSource.getDigimonByName(name)) {
            is Either.Left -> Either.Left(localDigimon.error)
            is Either.Right ->  {
                if (localDigimon.success == null ) {
                    return when (val remoteDigimon = digimonRemoteDataSource.getDigimonByName(name)) {
                        is Either.Left -> Either.Left(remoteDigimon.error)
                        is Either.Right -> {
                            when (val description = getDigimonDescription(name)) {
                                is Either.Left -> Either.Left(description.error)
                                is Either.Right -> {
                                    val digimon = DigimonEntity(
                                        name = remoteDigimon.success.name ?: "",
                                        imageUrl = remoteDigimon.success.img?: "",
                                        description = description.success,
                                        level = remoteDigimon.success.level ?: "",
                                    )
                                    val saveResult = digimonLocalDataSource.saveDigimon(digimon)
                                    when (saveResult) {
                                        is Either.Left -> Either.Left(saveResult.error)
                                        is Either.Right -> Either.Right(digimon.toDigimonUi())
                                    }
                                }
                            }
                        }
                    }
                }
                Either.Right(localDigimon.success.toDigimonUi())
            }
        }
    }

    private suspend fun getDigimonDescription(digimonName: String): Either<AppError, String> {
        val desc = geminiRepository.generateMessage("Dame una descripcion resumida y en castellano de $digimonName")
        return desc
    }
}