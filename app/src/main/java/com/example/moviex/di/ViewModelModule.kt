package com.example.moviex.di

import com.example.moviex.presentation.screen.auth.AuthViewModel
import com.example.moviex.presentation.screen.details.DetailsViewModel
import com.example.moviex.presentation.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { HomeViewModel(get(), get()) }

    viewModel { AuthViewModel(get()) }

    viewModel { DetailsViewModel(get()) }
}
