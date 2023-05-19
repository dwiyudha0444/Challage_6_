package com.dwiyu.ch5_moviedb.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavDAO {
    @Query("SELECT * FROM DataFav")
    fun getAllFavorite() : List<DataFav>

    @Query("SELECT EXISTS(SELECT id FROM DataFav WHERE id = :id)")
    fun isFavoriteMovie(id : Int) : Boolean

    @Insert
    fun addFavorite(favoriteMovie: DataFav)

    @Delete
    fun deleteFavorite(favoriteMovie: DataFav)
}