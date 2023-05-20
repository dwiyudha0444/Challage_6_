@file:Suppress("PrivatePropertyName")

package com.dwiyu.ch5_moviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dwiyu.ch5_moviedb.room.DataFav
import com.dwiyu.ch5_moviedb.room.FavDAO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("PrivatePropertyName")
@HiltViewModel
class ViewModelFav @Inject constructor(private val db: FavDAO) : ViewModel() {
    private val _movie: MutableLiveData<DataFav> = MutableLiveData()
    val movie: LiveData<DataFav> get() = _movie

    private val _ListMovie: MutableLiveData<List<DataFav>> = MutableLiveData()
    val listMovie: LiveData<List<DataFav>> get() = _ListMovie



    fun getAllFav() {
        GlobalScope.launch {
            _ListMovie.postValue(db.getAllFavorite())
        }
    }

}