package com.example.moviex.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResponse(
        @SerialName("id") val id: Long,
        @SerialName("adult") val isAdult: Boolean,
        @SerialName("backdrop_path") val backdropPath: String?,
        @SerialName("budget") val budget: Int,
        @SerialName("original_language") val originalLanguage: String,
        @SerialName("original_title") val originalTitle: String,
        @SerialName("title") val title: String,
        @SerialName("poster_path") val posterPath: String?,
        @SerialName("overview") val overview: String?,
        @SerialName("release_date") val releaseDate: String,
        @SerialName("popularity") val popularity: Double,
        @SerialName("runtime") val runtime: Int?,
        @SerialName("status") val status: String,
        @SerialName("vote_average") val voteAverage: Float,
        @SerialName("vote_count") val voteCount: Int,
        @SerialName("genres") val genres: List<GenreResponse>,
        @SerialName("production_companies") val productionCompanies: List<CompanyResponse>,
        @SerialName("production_countries") val productionCountries: List<CountryResponse>
) {
    @Serializable
    data class GenreResponse(
            @SerialName("id") val id: Int,
            @SerialName("name") val name: String
    )

    @Serializable
    data class CompanyResponse(
            @SerialName("name") val name: String,
            @SerialName("id") val id: Int,
            @SerialName("logo_path") val logoPath: String?,
            @SerialName("origin_country") val originCountry: String
    )

    @Serializable
    data class CountryResponse(
            @SerialName("iso_3166_1") val isoCode: String,
            @SerialName("name") val name: String
    )
}
