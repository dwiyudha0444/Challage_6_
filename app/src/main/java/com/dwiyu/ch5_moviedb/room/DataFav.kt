package com.dwiyu.ch5_moviedb.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class DataFav (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name = "release")
    var releasedate: String,
    @ColumnInfo(name = "posterpath")
    var posterPath : String
) : Serializable