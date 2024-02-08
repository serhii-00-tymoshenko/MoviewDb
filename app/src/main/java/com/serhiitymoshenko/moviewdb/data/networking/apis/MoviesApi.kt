package com.serhiitymoshenko.moviewdb.data.networking.apis

import com.serhiitymoshenko.moviewdb.data.networking.dto.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language")
        language: String,
        @Query("page")
        page: Int
    ) : Response<PopularMoviesResponse>
}