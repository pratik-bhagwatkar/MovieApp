package com.example.movieapp.screens.details

import android.graphics.drawable.Icon
import android.telecom.Call
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovieById
import com.example.movieapp.model.getMovies


@Composable
fun DetailsScreen(navController: NavController,id:String){

    Scaffold (
        topBar = {
            TopAppBar (
                title = {
                    Text(text = "Moview Details ")
                },
                elevation = 6.dp,
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Icon")
                    }
                }
            )
        },

    ){
          ShowMovieDetails(getMovieById(id))
    }
}




@Composable
fun ShowMovieDetails(movie: Movie) {

    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        LazyRow {
            items(movie.images){img->
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(img)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie Img",
                    placeholder = painterResource(id = R.drawable.ic_planet),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp)
                        .clip(shape = RectangleShape),

                    )
            }
        }


        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Movie Name: ${movie.title}",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Director: ${movie.director}",
            style = MaterialTheme.typography.subtitle2,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Released: ${movie.year}",
            style = MaterialTheme.typography.subtitle2,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Actors: ${movie.actors}",
            style = MaterialTheme.typography.subtitle2,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Plot: ${movie.plot}",
            style = MaterialTheme.typography.subtitle2,
        )


    }
}



