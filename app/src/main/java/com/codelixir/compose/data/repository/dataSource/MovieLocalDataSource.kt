package com.farhan.tanvir.data.repository.dataSource
import com.codelixir.compose.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getMoviesFromDB(movieId : Int): Flow<Movie>
}