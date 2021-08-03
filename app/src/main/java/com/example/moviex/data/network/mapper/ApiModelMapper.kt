package com.example.moviex.data.network.mapper

import com.example.moviex.data.network.model.*
import com.example.moviex.model.Movie
import com.example.moviex.model.MovieDetails
import com.example.moviex.model.User
import com.google.firebase.auth.FirebaseUser

class ApiModelMapper {

    fun map(response: FirebaseUser?): User? {
        return if (response == null) {
            null
        } else User(
                email = response.email,
                userName = response.displayName
        )
    }

    fun map(response: MovieResponse): Movie {
        return Movie(
            id = response.id,
            title = response.title,
            posterPath = response.posterPath,
            releaseDate = response.releaseDate,
            genreIds = response.genreIds,
        )
    }

    fun map(response: MovieDetailsResponse): MovieDetails {
        return MovieDetails(
                id = response.id,
                isAdult = response.isAdult,
                backdropPath = response.backdropPath,
                budget = response.budget,
                originalLanguage = response.originalLanguage,
                originalTitle = response.originalTitle,
                title = response.title,
                posterPath = response.posterPath,
                overview = response.overview,
                releaseDate = response.releaseDate,
                popularity = response.popularity,
                runtime = response.runtime,
                status = response.status,
                voteAverage = response.voteAverage,
                voteCount = response.voteCount,
                genres = response.genres.map(::map),
                productionCompanies = response.productionCompanies.map(::map),
                productionCountries = response.productionCountries.map(::map)
                )
    }

    fun map(response: MovieDetailsResponse.GenreResponse): MovieDetails.Genre {
        return MovieDetails.Genre(
                id = response.id,
                name = response.name
        )
    }

    fun map(response: MovieDetailsResponse.CompanyResponse):MovieDetails.Company {
        return MovieDetails.Company(
                name = response.name,
                id = response.id,
                logoPath = response.logoPath,
                originCountry = response.originCountry
        )
    }

    fun map(response: MovieDetailsResponse.CountryResponse): MovieDetails.Country {
        return MovieDetails.Country(
                isoCode = response.isoCode,
                name = response.name
        )
    }
}
