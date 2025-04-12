package org.marioonetti.firebasefundamentals.data.datasource.remote.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import org.marioonetti.firebasefundamentals.data.model.firebase.UserRequestDto
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.core.Success

class FirebaseRemoteDatasourceImpl: FirebaseRemoteDataSource {

    override suspend fun register(user: UserRequestDto): Either<AppError, Success> {
        val result = Firebase.auth.createUserWithEmailAndPassword(user.email, user.password)

        val uid = result.user?.uid

        return if (uid != null) {
            saveUserData(uid, user.username)
        } else {
            Either.Left(AppError.Remote("Error creating user"))
        }
    }

    override suspend fun isUserLoggedIn(): Either<AppError, Boolean> = execute {
        Firebase.auth.currentUser != null
    }

    override suspend fun logOut(): Either<AppError, Success> = execute {
        Firebase.auth.signOut()
        Success
    }

    override suspend fun logIn(email: String, password: String): Either<AppError, Success> = execute {
        Firebase.auth.signInWithEmailAndPassword(email, password)
        Success
    }

    private suspend fun saveUserData(
        uid: String,
        username: String
    ): Either<AppError, Success> {

        val user = mapOf(
            "username" to username,
            "email" to Firebase.auth.currentUser?.email,
            "createdAt" to dev.gitlive.firebase.firestore.FieldValue.serverTimestamp
        )

        try {
            Firebase.firestore.collection("users").document(uid).set(user)
            return Either.Right(Success)
        } catch (e: Exception) {
            return Either.Left(AppError.Remote("Error saving user data: ${e.message}"))
        }
    }

    private suspend fun <T> execute(f: suspend () -> T): Either<AppError, T> =
        try {
            Either.Right(f())
        } catch (e: Exception) {
            Either.Left(AppError.Remote(e.message ?: "An error occurred"))
        }
}