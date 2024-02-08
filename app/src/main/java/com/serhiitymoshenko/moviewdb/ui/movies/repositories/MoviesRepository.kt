package com.serhiitymoshenko.moviewdb.ui.movies.repositories

import com.serhiitymoshenko.moviewdb.data.networking.apis.MoviesApi

class MoviesRepository(private val moviesApi: MoviesApi) {

    suspend fun getPopularMovies(
        language: String,
        page: Int
    ) = moviesApi.getPopularMovies(language, page)
}