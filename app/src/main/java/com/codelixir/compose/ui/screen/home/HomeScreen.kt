package com.codelixir.compose.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codelixir.compose.ui.theme.ComposeTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.min
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTheme {
        GridSection()
    }
}

fun IntRange.random() =
    java.util.Random().nextInt((endInclusive + 1) - start) + start

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }

@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }

@Composable
fun GridSection(circleSize: Int = 80, circleCount: Int = 4) {
    var gridList: List<Int> by remember { mutableStateOf(listOf()) }

    val maxCircleCount: Int = min(circleCount, gridList.count()).run {
        if (gridList.count() == 5 || gridList.count() == 6) 3 else this
    }

    Column {
        Text(
            text = gridList.count().toString() + " :  " + maxCircleCount,
            modifier = Modifier.clickable {
                gridList = gridList + listOf(gridList.size)
            })
        BoxWithConstraints {
            val circleSizePx = circleSize.dp.dpToPx()
            val allCircleWidth = circleSizePx * maxCircleCount
            val availableSpace = maxWidth.dpToPx() - allCircleWidth
            val partialItemPadding =
                if (gridList.count() > 8) (circleSize.dp.dpToPx() / 5).toInt() else 0
            val singlePaddingPx =
                (availableSpace / (maxCircleCount - 1)).toInt().plus(partialItemPadding)

            val singlePadding = singlePaddingPx.pxToDp()

            val rows = if (gridList.count() > 4) 2 else 1

            val gridState = rememberLazyGridState()

            val coroutineScope = rememberCoroutineScope()

            LazyHorizontalGrid(
                state = gridState,
                horizontalArrangement = Arrangement.spacedBy(singlePadding),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                rows = GridCells.Fixed(rows),
                modifier = Modifier.height(circleSize.dp.times(rows))
            ) {
                items(gridList) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(circleSize.dp)
                            .width(circleSize.dp)
                            .clip(RoundedCornerShape(circleSize.dp))
                            .background(Color.Green)
                            .clickable {
                                if (it > 5)
                                    coroutineScope.launch {
                                        gridState.scrollToItem(
                                            it,
                                            -(singlePaddingPx + (circleSizePx * 0.66)).toInt()
                                        )
                                    }

                            }
                        //.padding(all = 4.dp)
                    ) {
                        Text(text = it.toString(), color = Color.White)
                    }

                }

            }

        }
    }

}

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val allMovies by viewModel.popularMovies.collectAsState()

    LaunchedEffect(Unit) {
        if (allMovies == null) {
            Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
            viewModel.getAllPopularMovies()
        }
    }

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()

    ) {
        Column {
            GreetingSection("Kaal")
            allMovies?.let {
                MovieListContent(it.movies)
            }
//            ChipSection(chips = listOf("Hamb", "Lamb", "Mamb", "Tamb", "Lamb", "Damb", "Kamb"))
//            CurMed()
//            CustomShapeView(color = Color.Blue)
//            CustomShapeView(color = Color.Red)
//            var text by remember { mutableStateOf("") }
//            TextField(value = text, onValueChange = { text = it })
//            Text(text = text)
        }
    }
}

@Composable
fun CustomShapeView(color: Color) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val radius = size.minDimension / 2
        drawCircle(
            color = color,
            radius = radius,
            center = Offset(size.width / 2, size.height / 2),
        )
        drawRoundRect(
            color = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()),
            alpha = 0.3f,
            cornerRadius = CornerRadius(size.height / 4)
        )
    }
}


@Composable
fun GreetingSection(name: String = "Name") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "Good Morning, $name")
            Text(text = "Have a good day!")
        }
        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_search),
            contentDescription = "Search",
            tint = Color.Green,
            modifier = Modifier.size(20.dp)
        )

    }
}

@Composable
fun ChipSection(chips: List<String>) {
    var selIndex by remember {
        mutableStateOf(0)
    }
    LazyRow() {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(if (selIndex == it) Color.Cyan else Color.Magenta)
                    .clickable { selIndex = it }
                    .padding(16.dp)
            ) {
                Text(text = chips[it], color = Color.White)
            }
        }
    }
}

@Composable
fun CurMed() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Blue)
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "Current")
            Text(text = "Have a good day!")
        }
        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_search),
            contentDescription = "Search",
            tint = Color.Green,
            modifier = Modifier.size(20.dp)
        )
    }
}