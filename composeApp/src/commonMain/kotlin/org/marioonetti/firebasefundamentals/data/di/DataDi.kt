package org.marioonetti.firebasefundamentals.data.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.marioonetti.firebasefundamentals.data.datasource.local.DigimonLocalDataSource
import org.marioonetti.firebasefundamentals.data.datasource.local.DigimonLocalDataSourceImpl
import org.marioonetti.firebasefundamentals.data.datasource.local.db.DatabaseProvider
import org.marioonetti.firebasefundamentals.data.datasource.local.db.RoomDb
import org.marioonetti.firebasefundamentals.data.datasource.remote.digimon.DigimonRemoteDataSource
import org.marioonetti.firebasefundamentals.data.datasource.remote.digimon.DigimonRemoteDataSourceImpl
import org.marioonetti.firebasefundamentals.data.datasource.remote.firebase.FirebaseRemoteDataSource
import org.marioonetti.firebasefundamentals.data.datasource.remote.firebase.FirebaseRemoteDatasourceImpl
import org.marioonetti.firebasefundamentals.data.datasource.remote.gemini.GeminiService
import org.marioonetti.firebasefundamentals.data.datasource.remote.gemini.GeminiServiceImpl

val dataModule = module {
    single<FirebaseRemoteDataSource> { FirebaseRemoteDatasourceImpl() }
    single<DigimonRemoteDataSource> { DigimonRemoteDataSourceImpl(get()) }
    single<GeminiService> { GeminiServiceImpl(get()) }
    single<DigimonLocalDataSource> { DigimonLocalDataSourceImpl(get()) }

    single<RoomDb> { DatabaseProvider(get()).getDatabase() }

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    }
                )
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                }
            }
        }
    }
}