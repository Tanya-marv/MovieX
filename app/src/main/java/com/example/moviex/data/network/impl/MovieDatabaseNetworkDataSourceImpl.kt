package com.example.moviex.data.network.impl

import com.example.moviex.data.network.MovieDatabaseNetworkDataSource
import com.example.moviex.data.network.api.MovieDatabaseApi
import com.example.moviex.data.network.mapper.ApiModelMapper
import com.example.moviex.model.Movie
import com.example.moviex.model.MovieDetails
import com.example.moviex.util.fp.Try

class MovieDatabaseNetworkDataSourceImpl(
        private val api: MovieDatabaseApi,
        private val mapper: ApiModelMapper,
        private val apiKey: String,
) : MovieDatabaseNetworkDataSource {

    override suspend fun fetchPopularMovies(): Try<List<Movie>> = Try.from {
        api.fetchPopularMovies(apiKey).results.map { it.let(mapper::map) }
    }

    override suspend fun fetchTopRatedMovies(): Try<List<Movie>> = Try.from {
        api.fetchTopRatedMovies(apiKey).results.map { it.let(mapper::map) }
    }

    override suspend fun fetchUpcomingMovies(): Try<List<Movie>> = Try.from {
        api.fetchUpcomingMovies(apiKey).results.map { it.let(mapper::map) }
    }

    override suspend fun fetchMovieDetails(id: Long): Try<MovieDetails> = Try.from {
        api.fetchMovieDetails(id, apiKey)
                .let(mapper::map)
    }
}
