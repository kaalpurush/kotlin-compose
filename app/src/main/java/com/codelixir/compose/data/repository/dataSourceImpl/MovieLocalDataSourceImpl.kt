package com.farhan.tanvir.data.repository.dataSourceImpl

import com.codelixir.compose.data.db.MovieDao
import com.codelixir.compose.domain.model.Movie
import com.farhan.tanvir.data.repository.dataSource.MovieLocalDataSource
import kotlinx.coroutines.flow.Flow


class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override fun getMoviesFromDB(movieId: Int): Flow<Movie> = movieDao.getMovie(movieId)
}