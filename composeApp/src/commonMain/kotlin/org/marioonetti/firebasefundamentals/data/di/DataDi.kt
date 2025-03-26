package org.marioonetti.firebasefundamentals.data.di

import org.koin.dsl.module
import org.marioonetti.firebasefundamentals.data.datasource.remote.RemoteDataSource
import org.marioonetti.firebasefundamentals.data.datasource.remote.RemoteDatasourceImpl

val dataModule = module {
    factory<RemoteDataSource> { RemoteDatasourceImpl() }
}