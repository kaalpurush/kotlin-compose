package com.codelixir.compose.domain.useCase

import com.codelixir.compose.domain.repository.MovieRepository


class GetMoviesFromDBUseCase(private val movieRepository: MovieRepository) {
    operator fun invoke(movieID: Int) = movieRepository.getMoviesFromDB(movieID)
}