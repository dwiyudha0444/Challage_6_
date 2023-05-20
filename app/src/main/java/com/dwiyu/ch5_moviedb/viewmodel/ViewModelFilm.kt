package com.dwiyu.ch5_moviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dwiyu.ch5_moviedb.model.NowPlayingMovieResponse
import com.dwiyu.ch5_moviedb.model.NowplayingMovie
import com.dwiyu.ch5_moviedb.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelFilm @Inject constructor(
    private val movieClient: ApiService,
) : ViewModel() {
    private val _movie: MutableLiveData<List<NowPlayingMovieResponse>> = MutableLiveData()
    val movie: LiveData<List<NowPlayingMovieResponse>> get() = _movie

    fun callApi() {
        movieClient.getMovie()
            .enqueue(object : Callback<NowplayingMovie> {
                override fun onResponse(
                    call: Call<NowplayingMovie>,
                    response: Response<NowplayingMovie>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            _movie.postValue(data.results as List<NowPlayingMovieResponse>?)
                        }
                    }
                }

                override fun onFailure(call: Call<NowplayingMovie>, t: Throwable) {

                }

            })
    }
}