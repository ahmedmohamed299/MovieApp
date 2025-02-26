package banquemisr.challenge05.movieApp.presentation.nowPlaying

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banquemisr.challenge05.movieApp.common.Constants
import banquemisr.challenge05.movieApp.domain.model.movie.Movie
import banquemisr.challenge05.movieApp.ui.theme.text
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Size

/**
 * MovieItem view, is the grid cell on playing now movies page
 *
 * @param movie will be the movie object from domain package
 * @param onMovieClick is the click event of the movie
 */
@Composable
fun MovieItemView(movie: Movie, onMovieClick: (Movie) -> Unit) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("${Constants.POSTER_URL}${movie.poster_path}")
            .memoryCachePolicy(CachePolicy.ENABLED)
            .size(Size.ORIGINAL)
            .build(),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
            .clickable { onMovieClick(movie) }
    ) {
        Column() {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,

                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            MovieItemInfoView(movie = movie)

        }
    }
}

@Composable
fun MovieItemInfoView(movie: Movie) {
    Column() {
        Text(text = movie.original_title, fontSize = 14.sp, color = text , fontWeight = FontWeight.Bold)
        Text(text = movie.release_date, fontSize = 14.sp, color = text)
    }

}

@Composable
@Preview
fun MovieItemViewPreview() {
    MovieItemView(
        Movie(
            original_title = "Harry Potter",
            release_date = "2022-02-20",
            vote_average = 5.6,
            poster_path = "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg"
        ),
        {}
    )
}