package com.dwiyu.ch5_moviedb.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DataFav::class], version = 1)
abstract class FavDB : RoomDatabase() {
    abstract fun favoriteDao(): FavDAO
}