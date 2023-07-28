package com.codelixir.compose.di

import android.app.Application
import androidx.room.Room
import com.codelixir.compose.data.db.MovieDB
import com.codelixir.compose.data.db.MovieDao
import com.codelixir.compose.data.db.MovieRemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(app: Application): MovieDB =
        Room.databaseBuilder(app, MovieDB::class.java, "movie_db").fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMovieDao(movieDB: MovieDB) : MovieDao= movieDB.movieDao()

    @Provides
    fun provideMovieRemoteKeysDao(movieDB: MovieDB) : MovieRemoteKeysDao = movieDB.movieRemoteKeysDao()
}