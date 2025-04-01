package org.marioonetti.firebasefundamentals.data.datasource.remote

import org.marioonetti.firebasefundamentals.data.model.UserRequestDto
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.core.Success

interface RemoteDataSource {

    suspend fun register(user: UserRequestDto): Either<AppError, Success>

    suspend fun isUserLoggedIn(): Either<AppError, Boolean>

    suspend fun logOut(): Either<AppError, Success>

    suspend fun logIn(email: String, password: String): Either<AppError, Success>

}