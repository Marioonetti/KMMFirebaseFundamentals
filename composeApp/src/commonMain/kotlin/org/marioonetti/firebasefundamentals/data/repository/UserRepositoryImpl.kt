package org.marioonetti.firebasefundamentals.data.repository

import org.marioonetti.firebasefundamentals.data.datasource.remote.RemoteDataSource
import org.marioonetti.firebasefundamentals.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
): UserRepository {

    override suspend fun register(email: String, password: String) = remoteDataSource.register(email, password)

}