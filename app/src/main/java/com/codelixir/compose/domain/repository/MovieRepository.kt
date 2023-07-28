package com.codelixir.compose.domain.repository

import androidx.paging.PagingData
import com.codelixir.compose.domain.model.Movie
import com.codelixir.compose.domain.model.MovieList
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    fun getPopularMovies(): Flow<MovieList>
    fun getMoviesFromDB(movieId: Int): Flow<Movie>
}