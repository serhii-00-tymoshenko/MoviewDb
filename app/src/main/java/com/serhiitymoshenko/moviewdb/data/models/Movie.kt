package com.serhiitymoshenko.moviewdb.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val originalTitle: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String
) : Parcelable