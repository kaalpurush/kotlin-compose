package com.codelixir.compose.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codelixir.compose.domain.model.Movie
import com.codelixir.compose.domain.model.MovieRemoteKeys

@Database(
    entities = [Movie::class, MovieRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieRemoteKeysDao(): MovieRemoteKeysDao
}