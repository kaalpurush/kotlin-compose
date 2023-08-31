package com.codelixir.compose.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelixir.compose.domain.model.Movie
import com.codelixir.compose.domain.model.MovieList
import com.codelixir.compose.domain.useCase.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases,
) : ViewModel() {
    private val _popularMovies: MutableStateFlow<MovieList?> = MutableStateFlow(null)
    val popularMovies = _popularMovies.asStateFlow()

    fun getAllPopularMovies() {
        viewModelScope.launch {
            movieUseCases.getPopularMoviesUseCase.invoke().collect {
                Log.d("HomeViewModel", "getAllPopularMovies: ${it.movies.count()}: ${it.toJson()}")
                _popularMovies.value = it
            }
        }
    }

}