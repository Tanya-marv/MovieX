package com.example.moviex.model

data class MovieDetails(
        val id: Long,
        val isAdult: Boolean,
        val backdropPath: String?,
        val budget: Int,
        val originalLanguage: String,
        val originalTitle: String,
        val title: String,
        val posterPath: String?,
        val overview: String?,
        val releaseDate: String,
        val popularity: Double,
        val runtime: Int?,
        val status: String,
        val voteAverage: Float,
        val voteCount: Int,
        val genres: List<Genre>,
        val productionCompanies: List<Company>,
        val productionCountries: List<Country>
) {
    data class Genre(
            val id: Int,
            val name: String
    )

    data class Company(
            val name: String,
            val id: Int,
            val logoPath: String?,
            val originCountry: String
    )

    data class Country(
            val isoCode: String,
            val name: String
    )
}