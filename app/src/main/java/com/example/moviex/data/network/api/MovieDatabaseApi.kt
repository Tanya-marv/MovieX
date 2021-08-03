package com.example.moviex.data.network.api

import com.example.moviex.data.network.model.MovieDetailsResponse
import com.example.moviex.data.network.model.MoviePageResponse
import com.example.moviex.data.network.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDatabaseApi {

    @GET("movie/popular")
    suspend fun fetchPopularMovies(
        @Query("api_key") apiKey: String
    ): MoviePageResponse

    @GET( "movie/top_rated")
    suspend fun fetchTopRatedMovies(
            @Query("api_key") apiKey: String
    ): MoviePageResponse

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(
            @Query("api_key") apiKey: String
    ): MoviePageResponse

    @GET("movie/{id}")
    suspend fun fetchMovieDetails(
            @Path("id") id: Long,
            @Query("api_key") apiKey: String
    ): MovieDetailsResponse
}
