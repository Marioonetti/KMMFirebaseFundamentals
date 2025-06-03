package org.marioonetti.firebasefundamentals.data.repository

import org.marioonetti.firebasefundamentals.data.datasource.local.DigimonLocalDataSource
import org.marioonetti.firebasefundamentals.data.datasource.remote.digimon.DigimonRemoteDataSource
import org.marioonetti.firebasefundamentals.data.datasource.remote.firebase.FirebaseRemoteDataSource
import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonEntity
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.core.Success
import org.marioonetti.firebasefundamentals.domain.mappers.toDigimonUi
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi
import org.marioonetti.firebasefundamentals.domain.repository.DigimonRepository
import org.marioonetti.firebasefundamentals.domain.repository.GeminiRepository

class DigimonRepositoryImpl(
    private val digimonRemoteDataSource: DigimonRemoteDataSource,
    private val digimonLocalDataSource: DigimonLocalDataSource,
    private val firebaseRemoteDataSource: FirebaseRemoteDataSource,
    private val geminiRepository: GeminiRepository,
): DigimonRepository {
    override suspend fun getAllDigimon() = digimonRemoteDataSource.getAllDigimon()

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

    override suspend fun saveFavDigimon(digimon: DigimonUi): Either<AppError, Success> {
        val isDigimonFavResult = firebaseRemoteDataSource.checkFavDigimon(digimon.name)
        return if (isDigimonFavResult.isRight() && isDigimonFavResult.getRight()) {
            firebaseRemoteDataSource.removeFavDigimon(digimon.name)
        } else {
            firebaseRemoteDataSource.saveFavDigimon(digimon)
        }
    }

    override suspend fun getAllFavDigimonByUser(): Either<AppError, List<DigimonUi>> = firebaseRemoteDataSource.getAllFavDigimonByUser()

    override suspend fun checkFavDigimon(name: String): Either<AppError, Boolean> = firebaseRemoteDataSource.checkFavDigimon(name)

    private suspend fun getDigimonDescription(digimonName: String): Either<AppError, String> {
        val desc = geminiRepository.generateMessage("Generate just the following text, remove all unnecessary comments: a short description of the digimon: $digimonName " +
                "and give it some attacks under the description in the next format: attackName: attackDescription. the attack description has to be in one line")
        return desc
    }
}