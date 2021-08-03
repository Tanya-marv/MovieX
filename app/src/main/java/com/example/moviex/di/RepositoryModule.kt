package com.example.moviex.di

import com.example.moviex.data.repository.MovieRepository
import com.example.moviex.data.repository.UserRepository
import com.example.moviex.data.repository.impl.MovieRepositoryImpl
import com.example.moviex.data.repository.impl.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<MovieRepository> { MovieRepositoryImpl(get()) }

    single<UserRepository> { UserRepositoryImpl(get())}
}
