package com.farhan.tanvir.androidcleanarchitecture.di

import com.codelixir.compose.domain.repository.MovieRepository
import com.codelixir.compose.data.repository.MovieRepositoryImpl
import com.farhan.tanvir.data.repository.dataSource.MovieLocalDataSource
import com.codelixir.compose.data.repository.dataSource.MovieRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMoviesRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource
    ): MovieRepository =
        MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource = movieLocalDataSource)
}