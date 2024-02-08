package com.serhiitymoshenko.moviewdb.data.models.converters

import com.serhiitymoshenko.moviewdb.data.models.Movie
import com.serhiitymoshenko.moviewdb.data.networking.dto.PopularMoviesResponse

fun PopularMoviesResponse.Result.fromDtoResult() =
    Movie(
        this.id,
        this.originalTitle,
        this.popularity,
        "https://image.tmdb.org/t/p/w500/${this.posterPath}",
        this.releaseDate
    )

fun List<PopularMoviesResponse.Result>.fromDtoResults() =
    this.map { it.fromDtoResult() }