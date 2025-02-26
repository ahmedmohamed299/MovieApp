package banquemisr.challenge05.movieApp.data

import banquemisr.challenge05.movieApp.common.Constants
import banquemisr.challenge05.movieApp.data.model.MovieModel
import banquemisr.challenge05.movieApp.data.model.MoviesDetailsModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/3/movie/{kind}")
    suspend fun getNowPlayingMovies(
        @Path("kind") kind: String = "now_playing",
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") lan: String = "en-US",
        @Query("page") page: String = "1"
    ): MovieModel
    @GET("/3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId:String,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") lan: String = "en-US"
    ): MoviesDetailsModel
}