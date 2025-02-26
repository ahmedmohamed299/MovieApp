package banquemisr.challenge05.movieApp.data.repoImpl

import banquemisr.challenge05.movieApp.data.ApiService
import banquemisr.challenge05.movieApp.domain.repository.MovieDetailsRepository

class MovieDetailsRepositoryImpl(private val api: ApiService): MovieDetailsRepository {
    override suspend fun getMoviesDetails(id:String)=api.getMovieDetails(id)
}