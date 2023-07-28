package com.codelixir.compose.data.repository

import com.codelixir.compose.domain.model.Movie
import com.codelixir.compose.domain.repository.MovieRepository
import com.farhan.tanvir.data.repository.dataSource.MovieLocalDataSource
import com.codelixir.compose.data.repository.dataSource.MovieRemoteDataSource
import com.codelixir.compose.domain.model.MovieList
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
) :
    MovieRepository {
    override fun getPopularMovies(): Flow<MovieList> =
        movieRemoteDataSource.getPopularMovies()

    override fun getMoviesFromDB(movieId: Int): Flow<Movie> =
        movieLocalDataSource.getMoviesFromDB(movieId)
}