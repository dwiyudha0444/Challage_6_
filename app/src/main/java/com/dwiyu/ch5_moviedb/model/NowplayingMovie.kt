package com.dwiyu.ch5_moviedb.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NowplayingMovie(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<NowPlayingMovieResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
): Serializable