package banquemisr.challenge05.movieApp.data.repositories

import banquemisr.challenge05.movieApp.data.model.MoviesDetailsModel
import banquemisr.challenge05.movieApp.domain.repository.MovieDetailsRepository
import banquemisr.challenge05.movieApp.domain.model.movieDetails.Genre
import java.net.SocketException

class FakeMovieDetailsRepository : MovieDetailsRepository {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getMoviesDetails(id: String): MoviesDetailsModel {
        if (shouldReturnNetworkError)
            throw SocketException("Unable to get the data object from server")
        else
            return MoviesDetailsModel(
                backdrop_path = "path",
                genres = listOf(Genre(0, "genre0"), Genre(1, "genre1")),
                id = 100,
                overview = "This is Overview",
                poster_path = "path",
                release_date = "2022-01-01",
                title = "Title"
            )
    }
}