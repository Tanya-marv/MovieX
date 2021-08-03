package com.example.moviex.presentation.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviex.data.repository.MovieRepository
import com.example.moviex.data.repository.UserRepository
import com.example.moviex.extensions.launch
import com.example.moviex.model.Movie
import com.example.moviex.presentation.base.viewModel.BaseViewModel
import com.example.moviex.util.fp.Try
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeViewModel(
    private val movieRepository: MovieRepository,
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _popularMoviesLiveData = MutableLiveData<List<Movie>>()
    private val _topRatedMoviesLiveData = MutableLiveData<List<Movie>>()
    private val _upcomingMoviesLiveData = MutableLiveData<List<Movie>>()

    val popularMoviesLiveData: LiveData<List<Movie>>
        get() = _popularMoviesLiveData

    val topRatedMoviesLiveData: LiveData<List<Movie>>
        get() = _topRatedMoviesLiveData

    val upcomingMoviesLiveData: LiveData<List<Movie>>
        get() = _upcomingMoviesLiveData

    fun retrievePopularMovies() = launch {
        movieRepository.retrievePopularMovies()
                .then { _popularMoviesLiveData.postValue(it) }
    }

    fun retrieveTopRatedMovies() = launch {
        movieRepository.retrieveTopRatedMovies()
                .then { _topRatedMoviesLiveData.postValue(it) }
    }

    fun retrieveUpcomingMovies() = launch {
        movieRepository.retrieveUpcomingMovies()
                .then { _upcomingMoviesLiveData.postValue(it) }
    }

    fun logOut() {
        userRepository.logOut()
    }
}
