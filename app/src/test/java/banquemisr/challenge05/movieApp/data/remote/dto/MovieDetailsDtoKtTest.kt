package banquemisr.challenge05.movieApp.data.remote.dto

import banquemisr.challenge05.movieApp.data.model.MoviesDetailsModel
import banquemisr.challenge05.movieApp.data.model.toMovieDetails
import banquemisr.challenge05.movieApp.domain.model.movieDetails.Genre
import com.google.common.truth.Truth.assertThat
import org.junit.Test


class MovieDetailsDtoKtTest {
    val movieDetailsDto = MoviesDetailsModel(
        backdrop_path = "path",
        genres = listOf(Genre(0, "genre0"), Genre(1, "genre1")),
        id = 100,
        overview = "This is Overview"
    )

    @Test
    fun `verify movieDetailsDto can generate movieDetail object isTrue`(){
        val details = movieDetailsDto.toMovieDetails()
        assertThat(details.genres.size == 2).isTrue()
    }

    @Test
    fun `verify movieDetailsDto_details set correct values isTrue`(){
        val details = movieDetailsDto.toMovieDetails()
        assertThat(details.backdrop_path == "path").isTrue()
    }
}