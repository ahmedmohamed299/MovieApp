package banquemisr.challenge05.movieApp.data.repoImpl

import banquemisr.challenge05.movieApp.data.ApiService
import banquemisr.challenge05.movieApp.domain.repository.MovieRepository

class MovieRepositoryImpl(private val api: ApiService): MovieRepository {
    override suspend fun getNowPlayingMovies(kind:String)=api.getNowPlayingMovies(kind)
}