package com.dwiyu.ch5_moviedb.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavDAO {
    @Query("SELECT * FROM DataFav")
    fun getAllFavorite() : List<DataFav>

    @Insert
    fun addFavorite(favoriteMovie: DataFav)

    @Delete
    fun deleteFavorite(favoriteMovie: DataFav)
}