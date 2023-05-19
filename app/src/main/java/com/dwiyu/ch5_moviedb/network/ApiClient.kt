package com.dwiyu.ch5_moviedb.network

import android.content.Context
import androidx.room.Room
import com.dwiyu.ch5_moviedb.room.FavDAO
import com.dwiyu.ch5_moviedb.room.FavDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiClient {
    @Singleton
    @Provides
    fun getMovieFilm(): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideFavoriteDAO(db : FavDB) : FavDAO = db.favoriteDao()

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context) : FavDB {
        return Room.databaseBuilder(context, FavDB::class.java, "favorite.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}