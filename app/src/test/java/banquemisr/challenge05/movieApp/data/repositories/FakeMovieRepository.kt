package banquemisr.challenge05.movieApp.data.repositories

import banquemisr.challenge05.movieApp.data.model.Dates
import banquemisr.challenge05.movieApp.data.model.MovieItem
import banquemisr.challenge05.movieApp.data.model.MovieModel
import banquemisr.challenge05.movieApp.domain.repository.MovieRepository
import java.net.SocketException

class FakeMovieRepository : MovieRepository {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getNowPlayingMovies(kind:String): MovieModel {
        if (shouldReturnNetworkError)
            throw SocketException("Unable to get the data object from server")
        else
            return MovieModel(
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
    }






}