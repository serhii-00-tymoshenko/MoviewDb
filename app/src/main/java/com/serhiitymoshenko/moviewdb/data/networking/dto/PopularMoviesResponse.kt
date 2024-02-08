package com.serhiitymoshenko.moviewdb.data.networking.dto


import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    @SerializedName("results")
    val results: List<Result>,
) {

    data class Result(
        @SerializedName("api")
        val id: Int,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
    )
}