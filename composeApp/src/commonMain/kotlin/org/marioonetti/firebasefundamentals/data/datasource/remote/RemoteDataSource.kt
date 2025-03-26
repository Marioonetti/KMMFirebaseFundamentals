package org.marioonetti.firebasefundamentals.data.datasource.remote

import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.core.Success

interface RemoteDataSource {

    suspend fun register(email: String, password: String): Either<AppError, Success>

}