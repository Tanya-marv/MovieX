package com.example.moviex.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviePageResponse (
    @SerialName("page") val page: Long,
    @SerialName("results") val results: List<MovieResponse>
)


