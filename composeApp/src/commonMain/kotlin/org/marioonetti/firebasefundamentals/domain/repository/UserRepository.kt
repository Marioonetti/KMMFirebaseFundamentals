package org.marioonetti.firebasefundamentals.domain.repository

import org.marioonetti.firebasefundamentals.data.model.firebase.UserRequestDto
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.core.Success
import org.marioonetti.firebasefundamentals.domain.models.User

interface UserRepository {

    suspend fun register(user: UserRequestDto): Either<AppError, Success>

    suspend fun isUserLoggedIn(): Either<AppError, Boolean>

    suspend fun logOut(): Either<AppError, Success>

    suspend fun logIn(email: String, password: String): Either<AppError, Success>

    suspend fun getUserData(): Either<AppError, User>

}