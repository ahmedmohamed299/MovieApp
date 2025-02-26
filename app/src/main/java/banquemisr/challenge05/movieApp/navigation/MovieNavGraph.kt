package banquemisr.challenge05.movieApp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import banquemisr.challenge05.movieApp.presentation.movieDetails.MovieDetailScreen
import banquemisr.challenge05.movieApp.presentation.nowPlaying.PlayingNowScreen

@Composable
fun MovieNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MovieScreenList.PlayingNow.route){
//        composable(route = MovieScreenList.SplashScreen.route){
//            AnimatedSplashScreen(navController)
//        }

        composable(route = MovieScreenList.PlayingNow.route){
            PlayingNowScreen(navController)
        }
        // will act as an deeplink, param will be movie id
        composable(route = MovieScreenList.MovieDetail.route+"/{movie_id}"){
            MovieDetailScreen(navController, it.arguments?.getString("movie_id"))
        }
    }
}