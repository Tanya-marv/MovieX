package com.example.moviex.data.network

import com.example.moviex.model.Movie
import com.example.moviex.model.MovieDetails
import com.example.moviex.util.fp.Try

interface MovieDatabaseNetworkDataSource {
    suspend fun fetchPopularMovies(): Try<List<Movie>>

    suspend fun fetchTopRatedMovies(): Try<List<Movie>>

    suspend fun fetchUpcomingMovies(): Try<List<Movie>>

    suspend fun fetchMovieDetails(id: Long): Try<MovieDetails>
}
