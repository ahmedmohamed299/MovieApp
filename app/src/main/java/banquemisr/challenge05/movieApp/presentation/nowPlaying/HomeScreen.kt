package banquemisr.challenge05.movieApp.presentation.nowPlaying

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import banquemisr.challenge05.movieApp.common.Constants
import banquemisr.challenge05.movieApp.common.LoadingView
import banquemisr.challenge05.movieApp.common.StandardToolbar
import banquemisr.challenge05.movieApp.navigation.MovieScreenList
import banquemisr.challenge05.movieApp.ui.theme.genreTextNight
import banquemisr.challenge05.movieApp.ui.theme.text
import banquemisr.challenge05.movieApp.ui.theme.textRating
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlayingNowScreen(
    navController: NavController?,
    movieViewModel: MovieViewModel = koinViewModel()
) {

    val popularState = movieViewModel.popularState.value
    val upcomingState = movieViewModel.upcomingState.value
    val nowPlayingState = movieViewModel.nowPlayingState.value

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("Movie App")
                }
            )
        },
        modifier = Modifier.background(Color.Black)
    )
    { padding->
        LazyColumn( modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color.Black)
        ,contentPadding = PaddingValues(horizontal = 18.dp)
        ) {
            item {
                MoviesList(nowPlayingState, "Now Playing",navController)

            }
            item {
                MoviesList(popularState, "popular Movies",navController)

            }
            item {
                MoviesList(upcomingState, "Upcoming Movies",navController)

            }
        }


    }


}

@Composable
fun MoviesList(state: PlayingNowListState, title: String,navController: NavController?) {

    if (!state.isLoading) {
        Column(modifier = Modifier.height(300.dp)) {


            Text(
                text = title,
                fontSize = 18.sp,
                color = text,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            LazyHorizontalGrid(GridCells.Fixed(1)) {
                items(state.movies.size) { index ->
                    MovieItemView(movie = state.movies[index], onMovieClick = {
                        navController?.navigate(MovieScreenList.MovieDetail.route + "/${state.movies[index].id}")
                    })
                }
                item {
                    Column() {
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                }
            }
        }
    }
    if (state.error.isNotBlank()) {

        Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_SHORT).show()

    }
    if (state.isLoading) {
        LoadingView()
    }
}

@Composable
@Preview
fun PlayingNowScreenPreview() {
    PlayingNowScreen(null)
}