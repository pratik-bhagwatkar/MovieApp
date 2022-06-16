package com.example.movieapp.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.navigation.NavigationSealed

@Composable
fun HomeScreen(navController: NavController){
    CreateAppBar(navController)
}



@Composable
fun CreateAppBar(navController: NavController){
    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Movies")
                },
                elevation = 6.dp
            )
        }
    ) {
        CreateListForMovies(getMovies(),navController)
    }
}

@Composable
fun CreateListForMovies(movieList: List<Movie>,navController: NavController)
{
    LazyColumn{
        items(movieList){ item ->
            CreateListItemCard(item){
                navController.navigate(route = NavigationSealed.DetailScreen.createRoute(it))
            }
        }
    }
}
@Preview
@Composable
fun CreateListItemCard(movie : Movie= getMovies()[0], onItemClick:(String) -> Unit={}){
    val expand = remember {
        mutableStateOf(false)
    }
    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            onItemClick(movie.id)
        },
        elevation = 6.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ){

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ){

                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie Img",
                    placeholder = painterResource(id = R.drawable.ic_planet),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(8.dp)
                        .clip(shape = CircleShape),

                    )

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Director: ${movie.director}",
                        style = MaterialTheme.typography.subtitle1,
                    )

                    Text(
                        text = "Released: ${movie.year}",
                        style = MaterialTheme.typography.subtitle1,
                    )

                    Icon(
                        imageVector = if (expand.value) Icons.Filled.KeyboardArrowUp
                        else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Down Arrow",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                expand.value = !expand.value
                            },
                        tint = Color.DarkGray,
                    )

                    AnimatedVisibility(visible = expand.value) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.Red,
                                            fontSize = 15.sp
                                        )
                                    ){
                                        append("Plot: ")
                                    }

                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.DarkGray,
                                            fontSize = 15.sp
                                        )
                                    ){
                                        append(movie.plot)
                                    }
                                },
                            )

                            Divider()

                            Text(

                                text = "Actor : ${movie.actors}",
                                style = MaterialTheme.typography.caption,
                            )

                            Text(
                                text = "Rating : ${movie.rating}",
                                style = MaterialTheme.typography.caption,

                            )
                        }
                }


            }


            }




    }
}


