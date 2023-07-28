package com.codelixir.compose.domain.useCase

import com.codelixir.compose.domain.useCase.GetMoviesFromDBUseCase
import com.codelixir.compose.domain.useCase.GetPopularMoviesUseCase


data class MovieUseCases(
    val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    val getMoviesFromDBUseCase: GetMoviesFromDBUseCase,
)
