package com.codelixir.compose.domain.useCase

import com.codelixir.compose.BuildConfig
import com.codelixir.compose.domain.repository.MovieRepository

class GetPopularMoviesUseCase(private val movieRepository: MovieRepository) {
    operator fun invoke() = movieRepository.getPopularMovies()
}