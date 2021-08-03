package com.example.moviex.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("genre_ids") val genreIds: List<Int> = emptyList()
)
