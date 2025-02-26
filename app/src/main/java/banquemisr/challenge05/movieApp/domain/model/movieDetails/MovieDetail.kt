package banquemisr.challenge05.movieApp.domain.model.movieDetails

data class MovieDetail(
    val backdrop_path: String = "",
    val genres: List<Genre> = emptyList(),
    val id: Int = 0,
    val original_title: String = "",
    val overview: String = "",
    val poster_path: String = "",
    val release_date: String = "",
    val runtime: Int = 0,
    val title: String = "",
)
