package com.farhan.tanvir.androidcleanarchitecture.di

import com.codelixir.compose.data.api.MovieApi
import com.codelixir.compose.data.db.MovieDB
import com.codelixir.compose.data.repository.dataSource.MovieRemoteDataSource
import com.codelixir.compose.data.repository.dataSourceImpl.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideMoviesRemoteDataSource(movieApi: MovieApi, movieDB: MovieDB) : MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(movieApi, movieDB = movieDB)
}