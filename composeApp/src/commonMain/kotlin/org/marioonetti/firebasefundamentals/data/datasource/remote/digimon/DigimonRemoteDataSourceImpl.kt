package org.marioonetti.firebasefundamentals.data.datasource.remote.digimon

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonDto
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.mappers.toDigimonUi
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi

private const val BASE_URL = "https://digimon-api.vercel.app/api/digimon"

class DigimonRemoteDataSourceImpl(
    private val client: HttpClient
) : DigimonRemoteDataSource {

    override suspend fun getAllDigimon(): Either<AppError, List<DigimonUi>> {
        val resultAll = fetchDigimons()
        return if (resultAll.isRight()) {
            Either.Right(resultAll.getRight())
        } else {
            Either.Left(resultAll.getLeft())
        }
    }

     override suspend fun getDigimonByName(name: String): Either<AppError, DigimonDto> {
        return try {
            val response: List<DigimonDto> = client.get("$BASE_URL/name/$name")
                .body()
            Either.Right(response.first())
        } catch (e: Exception) {
            Either.Left(AppError.Remote(e.message ?: "Unknown error"))
        }
    }

    private suspend fun fetchDigimons(): Either<AppError, List<DigimonUi>> {
        return try {
            val response: List<DigimonUi> = client.get(BASE_URL).body<List<DigimonDto>>().map { it.toDigimonUi() }
            Either.Right(response)
        } catch (e: Exception) {
            Either.Left(AppError.Remote(e.message ?: "Unknown error"))
        }
    }

}