package banquemisr.challenge05.movieApp.domain.repository

import banquemisr.challenge05.movieApp.data.model.MoviesDetailsModel

interface MovieDetailsRepository {
    suspend fun getMoviesDetails(id:String): MoviesDetailsModel

}