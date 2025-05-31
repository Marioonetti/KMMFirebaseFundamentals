package org.marioonetti.firebasefundamentals.data.repository

import org.marioonetti.firebasefundamentals.data.datasource.remote.firebase.FirebaseRemoteDataSource
import org.marioonetti.firebasefundamentals.data.model.firebase.UserRequestDto
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.core.Success
import org.marioonetti.firebasefundamentals.domain.models.User
import org.marioonetti.firebasefundamentals.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteDataSource: FirebaseRemoteDataSource,
): UserRepository {

    override suspend fun register(user: UserRequestDto): Either<AppError, Success> = remoteDataSource.register(user)

    override suspend fun isUserLoggedIn(): Either<AppError, Boolean> = remoteDataSource.isUserLoggedIn()

    override suspend fun logOut(): Either<AppError, Success> = remoteDataSource.logOut()

    override suspend fun logIn(email: String, password: String): Either<AppError, Success> = remoteDataSource.logIn(email, password)

    override suspend fun getUserData(): Either<AppError, User>  = remoteDataSource.getUserData()

}