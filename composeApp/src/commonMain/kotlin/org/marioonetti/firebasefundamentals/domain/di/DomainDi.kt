package org.marioonetti.firebasefundamentals.domain.di

import org.koin.dsl.module
import org.marioonetti.firebasefundamentals.data.repository.UserRepositoryImpl
import org.marioonetti.firebasefundamentals.domain.repository.UserRepository

val domainModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}