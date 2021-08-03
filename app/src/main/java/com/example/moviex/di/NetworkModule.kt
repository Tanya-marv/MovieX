package com.example.moviex.di

import com.example.moviex.BuildConfig
import com.example.moviex.data.network.MovieDatabaseNetworkDataSource
import com.example.moviex.data.network.UserNetworkDataSource
import com.example.moviex.data.network.api.MovieDatabaseApi
import com.example.moviex.data.network.impl.MovieDatabaseNetworkDataSourceImpl
import com.example.moviex.data.network.impl.UserNetworkDataSourceImpl
import com.example.moviex.data.network.mapper.ApiModelMapper
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                }
            )
            .build()
    }

    single<MovieDatabaseApi> {
        val contentType = "application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl(get<String>(named("baseUrl")))
            .addConverterFactory(Json{ignoreUnknownKeys = true}.asConverterFactory(contentType))
            .client(get())
            .build()
            .create()
    }

    single<MovieDatabaseNetworkDataSource> {
        MovieDatabaseNetworkDataSourceImpl(get(), get(), get(named("apiKey")))
    }

    single<UserNetworkDataSource> {
        UserNetworkDataSourceImpl(get())
    }

    single { ApiModelMapper() }

    single(named("baseUrl")) { BuildConfig.MOVIE_DATABASE_BASE_URL }

    single(named("apiKey")) { BuildConfig.MOVIE_DATABASE_API_KEY }
}
