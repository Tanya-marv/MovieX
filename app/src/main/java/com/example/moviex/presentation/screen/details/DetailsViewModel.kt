package com.example.moviex.presentation.screen.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviex.data.repository.MovieRepository
import com.example.moviex.extensions.launch
import com.example.moviex.model.Movie
import com.example.moviex.model.MovieDetails
import com.example.moviex.presentation.base.viewModel.BaseViewModel

class DetailsViewModel(
        private val movieRepository: MovieRepository
) : BaseViewModel() {
    private val _movieDetailsLiveData = MutableLiveData<MovieDetails>()

    val movieDetailsLiveData: LiveData<MovieDetails>
        get() = _movieDetailsLiveData

    fun retrieveMovieDetails(id: Long) = launch {
        movieRepository.retrieveMovieDetails(id)
                .then {
                    _movieDetailsLiveData.postValue(it)
                }
    }
}
