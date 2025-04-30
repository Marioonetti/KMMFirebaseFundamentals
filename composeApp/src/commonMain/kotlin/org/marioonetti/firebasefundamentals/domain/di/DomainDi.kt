package org.marioonetti.firebasefundamentals.domain.di

import org.koin.dsl.module
import org.marioonetti.firebasefundamentals.data.repository.DigimonRepositoryImpl
import org.marioonetti.firebasefundamentals.data.repository.GeminiRepositoryImpl
import org.marioonetti.firebasefundamentals.data.repository.UserRepositoryImpl
import org.marioonetti.firebasefundamentals.domain.repository.DigimonRepository
import org.marioonetti.firebasefundamentals.domain.repository.GeminiRepository
import org.marioonetti.firebasefundamentals.domain.repository.UserRepository

val domainModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<DigimonRepository> { DigimonRepositoryImpl(get(), get(), get(), get()) }
    single<GeminiRepository> { GeminiRepositoryImpl(get()) }
}