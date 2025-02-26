package banquemisr.challenge05.movieApp.presentation.movieDetails

import banquemisr.challenge05.movieApp.domain.model.movieDetails.MovieDetail

/**
 * State for movie details
 */
data class MovieDetailState(
    val isLoading: Boolean = false,
    val data: MovieDetail = MovieDetail(),
    val error: String = ""
)
