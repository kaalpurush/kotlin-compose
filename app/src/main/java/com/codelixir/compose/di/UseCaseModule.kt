package com.farhan.tanvir.androidcleanarchitecture.di

import com.codelixir.compose.domain.repository.MovieRepository
import com.codelixir.compose.domain.useCase.GetMoviesFromDBUseCase
import com.codelixir.compose.domain.useCase.GetPopularMoviesUseCase
import com.codelixir.compose.domain.useCase.MovieUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideMovieUseCases(movieRepository: MovieRepository) = MovieUseCases(
        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository = movieRepository),
        getMoviesFromDBUseCase = GetMoviesFromDBUseCase(movieRepository = movieRepository)
    )
}