package org.marioonetti.firebasefundamentals.data.datasource.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.marioonetti.firebasefundamentals.data.datasource.local.dao.DigimonDao
import org.marioonetti.firebasefundamentals.data.datasource.local.db.RoomDb
import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonEntity
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either

class DigimonLocalDataSourceImpl(
    private val database: RoomDb
): DigimonLocalDataSource {

    override suspend fun getDigimonByName(name: String): Either<AppError, DigimonEntity?> {
        return try {
            Either.Right(database.digimonDao().getDigimonByName(name))
        } catch (e: Exception) {
            Either.Left(AppError.Local(e.message ?: "Unknown error"))
        }
    }

    override suspend fun saveDigimon(digimon: DigimonEntity): Either<AppError, Unit> {
        return try {
            database.digimonDao().insert(digimon)
            Either.Right(Unit)
        } catch (e: Exception) {
            Either.Left(AppError.Local(e.message ?: "Unknown error"))
        }
    }
}