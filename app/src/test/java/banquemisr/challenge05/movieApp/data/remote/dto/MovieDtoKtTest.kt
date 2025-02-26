package banquemisr.challenge05.movieApp.data.remote.dto

import banquemisr.challenge05.movieApp.data.model.Dates
import banquemisr.challenge05.movieApp.data.model.MovieItem
import banquemisr.challenge05.movieApp.data.model.MovieModel
import banquemisr.challenge05.movieApp.data.model.toMovies
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MovieDtoKtTest {

    val movieModel = MovieModel(
        Dates("", ""),
        1,
        listOf(
            MovieItem(backdrop_path = "path", original_title = "title"),
            MovieItem(backdrop_path = "path1", original_title = "title1"),
            MovieItem(backdrop_path = "path2", original_title = "title2")
        ),
        2,
        6
    )

    @Test
    fun `verify movieDto can convert to listOfMovies isTrue`() {
        val listOfMovies = movieModel.toMovies()
        assertThat(listOfMovies.size == 3).isTrue()
    }

    @Test
    fun `verify movieDto_toMovies set correct data isTrue`() {
        val listOfMovies = movieModel.toMovies()
        assertThat(listOfMovies[1].backdrop_path == "path1").isTrue()
    }


}