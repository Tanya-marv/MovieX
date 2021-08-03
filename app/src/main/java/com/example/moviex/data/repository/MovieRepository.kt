package com.example.moviex.data.repository

import com.example.moviex.model.Movie
import com.example.moviex.model.MovieDetails
import com.example.moviex.util.fp.Try

interface MovieRepository {

    suspend fun retrievePopularMovies(): Try<List<Movie>>

    suspend fun retrieveTopRatedMovies(): Try<List<Movie>>

    suspend fun retrieveUpcomingMovies(): Try<List<Movie>>

    suspend fun retrieveMovieDetails(id: Long): Try<MovieDetails>
}
