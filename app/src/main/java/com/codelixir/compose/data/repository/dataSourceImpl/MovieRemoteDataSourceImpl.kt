package com.codelixir.compose.data.repository.dataSourceImpl

import androidx.paging.ExperimentalPagingApi
import com.codelixir.compose.BuildConfig
import com.codelixir.compose.data.api.MovieApi
import com.codelixir.compose.data.db.MovieDB
import com.codelixir.compose.data.repository.dataSource.MovieRemoteDataSource
import com.codelixir.compose.domain.model.MovieList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class MovieRemoteDataSourceImpl(private val movieApi: MovieApi, private val movieDB: MovieDB) :
    MovieRemoteDataSource {
    private val movieDao = movieDB.movieDao()

    override fun getPopularMovies(): Flow<MovieList> {
        return flow {
            movieApi.getPopularMovies(BuildConfig.API_KEY).body()?.let { emit(it) }
        }
    }
}