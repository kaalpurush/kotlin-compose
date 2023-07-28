package com.codelixir.compose.data.repository.dataSource

import com.codelixir.compose.domain.model.MovieList
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    fun getPopularMovies(): Flow<MovieList>
}