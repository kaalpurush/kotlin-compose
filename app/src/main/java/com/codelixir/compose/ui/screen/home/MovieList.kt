package com.codelixir.compose.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.codelixir.compose.BuildConfig
import com.codelixir.compose.R
import com.codelixir.compose.domain.model.Movie
import com.codelixir.compose.ui.theme.ComposeTheme


@Preview(showBackground = true)
@Composable
fun Preview() {
    ComposeTheme {
        MovieListItem(
            movie = Movie(
                0L, 1, "Barbie",
                "/iuFNMS8U5cb6xfzi51Dbkovj7vM.jpg", "Barbie",
                "7.5", "2023-07-19"
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .border(width = 1.dp, color = Color.Red)
                .height(180.dp)
        )
    }
}

@Composable
fun MovieListContent(allMovies: List<Movie>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        items(
            items = allMovies,
            key = { movie -> movie.movieId }
        ) { movie ->
            MovieListItem(
                movie = movie, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .height(180.dp)
            )
        }
    }
}

@Composable
fun MovieListItem(movie: Movie, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .fillMaxWidth()
                .clickable {
                    Toast
                        .makeText(context, movie.title, Toast.LENGTH_LONG)
                        .show()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            movie.posterPath?.let {
                Image(
                    modifier = Modifier
                        .padding(
                            end = 4.dp,
                        )
                        .fillMaxWidth(.25f),
                    painter = rememberImagePainter(
                        data = BuildConfig.POSTER_URL + movie.posterPath, builder = {
                            placeholder(R.drawable.ic_launcher_background)
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
