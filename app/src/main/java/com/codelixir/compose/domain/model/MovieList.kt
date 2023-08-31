package com.codelixir.compose.domain.model

import com.codelixir.compose.domain.model.Movie
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieList(
    @SerializedName("page")
    val page: Int = 1,
    @SerializedName("results")
    val movies: List<Movie>,
) : Serializable {
    fun toJson(): String = Gson().toJson(this)
}