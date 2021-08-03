package com.example.moviex.model


data class Movie(
    val id: Long,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val genreIds: List<Int>
)
