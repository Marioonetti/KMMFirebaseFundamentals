package org.marioonetti.firebasefundamentals.data.datasource.remote.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import org.marioonetti.firebasefundamentals.data.model.digimon.DigimonFirebaseDto
import org.marioonetti.firebasefundamentals.data.model.firebase.FireBaseUserDto
import org.marioonetti.firebasefundamentals.data.model.firebase.UserRequestDto
import org.marioonetti.firebasefundamentals.domain.core.AppError
import org.marioonetti.firebasefundamentals.domain.core.Either
import org.marioonetti.firebasefundamentals.domain.core.Success
import org.marioonetti.firebasefundamentals.domain.mappers.toDigimonFirebase
import org.marioonetti.firebasefundamentals.domain.mappers.toDigimonUi
import org.marioonetti.firebasefundamentals.domain.models.DigimonUi
import org.marioonetti.firebasefundamentals.domain.models.User

class FirebaseRemoteDatasourceImpl: FirebaseRemoteDataSource {

    override suspend fun register(user: UserRequestDto): Either<AppError, Success> {
        try {
            val result = Firebase.auth.createUserWithEmailAndPassword(user.email, user.password)

            val uid = result.user?.uid

            return if (uid != null) {
                saveUserData(uid, user.username)
            } else {
                Either.Left(AppError.Remote("Error creating user"))
            }
        } catch (e: Exception) {
            return Either.Left(AppError.Remote("Error registering user: ${e.message}"))
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

    override suspend fun getAllFavDigimonByUser(): Either<AppError, List<DigimonUi>> = execute {
        val uid = Firebase.auth.currentUser?.uid ?: ""
        val digimonList: List<DigimonFirebaseDto> = Firebase.firestore
            .collection("favourites")
            .document(uid)
            .collection("digimon")
            .get()
            .documents
            .map { it.data<DigimonFirebaseDto>() }

        digimonList.map { it.toDigimonUi() }
    }

    override suspend fun saveFavDigimon(digimon: DigimonUi): Either<AppError, Success> {
        val uid = Firebase.auth.currentUser?.uid
        if (uid != null) {
            try {
                Firebase.firestore
                    .collection("favourites")
                    .document(uid)
                    .collection("digimon")
                    .document(digimon.name)
                    .set(digimon.toDigimonFirebase())
                return Either.Right(Success)
            } catch (e: Exception) {
                return Either.Left(AppError.Remote("Error saving fav digimon: ${e.message}"))
            }
        }
        return Either.Left(AppError.Remote("User not logged in"))
    }

    override suspend fun checkFavDigimon(name: String): Either<AppError, Boolean> {
        val uid = Firebase.auth.currentUser?.uid
        if (uid != null) {
            try {
                val digimon = Firebase.firestore
                    .collection("favourites")
                    .document(uid)
                    .collection("digimon")
                    .document(name)
                    .get()
                    .data<DigimonFirebaseDto>()
                return Either.Right(true)
            }
            catch (e: IllegalArgumentException) {
                print("Error checking fav digimon because collection is null or the digimon doesn't exists: ${e.message}")
                return Either.Right(false)
            }
            catch (e: Exception) {
                return Either.Left(AppError.Remote("Error checking fav digimon: ${e.message}"))
            }
        }
        return Either.Left(AppError.Remote("User not logged in"))
    }

    override suspend fun removeFavDigimon(name: String): Either<AppError, Success> {
        val uid = Firebase.auth.currentUser?.uid
        if (uid != null) {
            try {
                Firebase.firestore
                    .collection("favourites")
                    .document(uid)
                    .collection("digimon")
                    .document(name)
                    .delete()
                return Either.Right(Success)
            } catch (e: Exception) {
                return Either.Left(AppError.Remote("Error removing fav digimon: ${e.message}"))
            }
        }
        return Either.Left(AppError.Remote("User not logged in"))
    }

    override suspend fun getUserData(): Either<AppError, User> {
        val uid = Firebase.auth.currentUser?.uid
        if (uid != null) {
            try {
                val firebaseUser = Firebase.firestore
                    .collection("users")
                    .document(uid)
                    .get()
                    .data<FireBaseUserDto>()

                return Either.Right(User(firebaseUser.username, firebaseUser.email))
            } catch (e: Exception) {
                return Either.Left(AppError.Remote("Error getting user data: ${e.message}"))
            }
        }
        return Either.Left(AppError.Remote("User not logged in"))
    }

    private suspend fun saveUserData(
        uid: String,
        username: String
    ): Either<AppError, Success> {
        try {
            val user = mapOf(
                "username" to username,
                "email" to Firebase.auth.currentUser?.email,
                "createdAt" to dev.gitlive.firebase.firestore.FieldValue.serverTimestamp
            )

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