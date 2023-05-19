package com.dwiyu.ch5_moviedb.network

import com.dwiyu.ch5_moviedb.model.NowplayingMovie
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movie/now_playing?api_key=9ae09583ab3404e7bbb8329c391fe2b5")
    fun getMovie(): Call<NowplayingMovie>
}