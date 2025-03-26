package org.marioonetti.firebasefundamentals.data.datasource.remote

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.core.Success

class RemoteDatasourceImpl: RemoteDataSource {

    override suspend fun register(email: String, password: String): Either<AppError, Success> {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
        return Either.Right(Success)
    }

    private suspend fun <T> execute(f: suspend () -> T): Either<AppError, T> =
        try {
            Either.Right(f())
        } catch (e: Exception) {
            Either.Left(AppError.Remote(e.message ?: "An error occurred"))
        }
}