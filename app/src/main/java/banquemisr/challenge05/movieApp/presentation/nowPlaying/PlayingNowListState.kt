package banquemisr.challenge05.movieApp.presentation.nowPlaying

import banquemisr.challenge05.movieApp.domain.model.movie.Movie

/**
 * State for playing now movies
 */
data class PlayingNowListState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = ""
)
