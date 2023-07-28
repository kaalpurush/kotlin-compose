package com.codelixir.compose.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.codelixir.compose.BuildConfig
import com.codelixir.compose.domain.model.Movie


@Composable
fun MovieListContent(allMovies: List<Movie>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        items(
            items = allMovies,
            key = { movie -> movie.movieId }
        ) { movie ->
            MovieListItem(movie = movie)
        }
    }
}

@Composable
fun MovieListItem(movie: Movie) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp)
            .height(180.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .fillMaxWidth()
                .clickable {

                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            movie.posterPath?.let {
                Image(
                    modifier = Modifier
                        .padding(
                            end = 4.dp,
                        )
                        .width(120.dp),
                    painter = rememberImagePainter(
                        data = BuildConfig.POSTER_URL + movie.posterPath, builder = {
                            crossfade(true)
                            scale(Scale.FILL)
                        }),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
            Column(
                Modifier
                    .height(IntrinsicSize.Max)
                    .padding(
                        end = 2.dp,
                    )
            ) {
                movie.title?.let { Text(text = it, style = MaterialTheme.typography.body1) }
                Spacer(modifier = Modifier.height(4.dp))
                movie.overview?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
