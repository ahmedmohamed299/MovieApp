package banquemisr.challenge05.movieApp.domain.repository

import banquemisr.challenge05.movieApp.data.model.MovieModel

interface MovieRepository {
    suspend fun getNowPlayingMovies(kind:String): MovieModel

}