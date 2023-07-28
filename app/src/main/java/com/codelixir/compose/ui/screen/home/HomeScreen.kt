package com.codelixir.compose.ui.screen.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect
import kotlin.random.Random

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    viewModel.getAllPopularMovies()
    val allMovies by viewModel.popularMovies.collectAsState()
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
//            var text by remember { mutableStateOf("") }
//            TextField(value = text, onValueChange = { text = it })
//            Text(text = text)
//            CustomShapeView(color = Color.Red)
//            CustomShapeView(color = Color.Blue)
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