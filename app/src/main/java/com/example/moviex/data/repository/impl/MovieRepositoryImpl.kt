package com.example.moviex.data.repository.impl

import com.example.moviex.data.network.MovieDatabaseNetworkDataSource
import com.example.moviex.data.repository.MovieRepository
import com.example.moviex.model.Movie
import com.example.moviex.model.MovieDetails
import com.example.moviex.util.fp.Try

class MovieRepositoryImpl(
    private val networkDataSource: MovieDatabaseNetworkDataSource
) : MovieRepository {

    override suspend fun retrievePopularMovies(): Try<List<Movie>> {
        return networkDataSource.fetchPopularMovies()
    }

    override suspend fun retrieveTopRatedMovies(): Try<List<Movie>> {
        return networkDataSource.fetchTopRatedMovies()
    }

    override suspend fun retrieveUpcomingMovies(): Try<List<Movie>> {
        return networkDataSource.fetchUpcomingMovies()
    }

    override suspend fun retrieveMovieDetails(id: Long): Try<MovieDetails> {
        return networkDataSource.fetchMovieDetails(id)
    }
}
