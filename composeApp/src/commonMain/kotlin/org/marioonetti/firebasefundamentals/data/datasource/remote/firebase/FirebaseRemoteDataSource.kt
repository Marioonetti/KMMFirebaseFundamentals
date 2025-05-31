package org.marioonetti.firebasefundamentals.data.datasource.remote.firebase

import org.marioonetti.firebasefundamentals.data.model.firebase.UserRequestDto
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.core.Success
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi
import org.marioonetti.firebasefundamentals.domain.models.User

interface FirebaseRemoteDataSource {

    suspend fun register(user: UserRequestDto): Either<AppError, Success>

    suspend fun isUserLoggedIn(): Either<AppError, Boolean>

    suspend fun logOut(): Either<AppError, Success>

    suspend fun logIn(email: String, password: String): Either<AppError, Success>

    suspend fun saveFavDigimon(digimon: DigimonUi): Either<AppError, Success>

    suspend fun getAllFavDigimonByUser(): Either<AppError, List<DigimonUi>>

    suspend fun checkFavDigimon(name: String): Either<AppError, Boolean>

    suspend fun removeFavDigimon(name: String): Either<AppError, Success>

    suspend fun getUserData(): Either<AppError, User>

}