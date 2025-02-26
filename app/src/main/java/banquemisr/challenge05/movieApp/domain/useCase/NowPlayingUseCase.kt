package banquemisr.challenge05.movieApp.domain.useCase

import banquemisr.challenge05.movieApp.domain.model.movie.Movie
import banquemisr.challenge05.movieApp.common.ResourceData
import banquemisr.challenge05.movieApp.data.model.toMovies
import banquemisr.challenge05.movieApp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException

class NowPlayingUseCase(private val repository: MovieRepository) {

    operator  fun invoke(kind: String):Flow<ResourceData<List<Movie>>> = flow {
        emit(ResourceData.loading())
        try {

            emit(ResourceData.success(repository.getNowPlayingMovies(kind).toMovies()))
        }catch (e: HttpException){
            emit(ResourceData.error<List<Movie>>(e.localizedMessage ?: "There is an exception occurred on HTTP Connection"))
        } catch (e: IOException){
            emit(ResourceData.error<List<Movie>>(e.localizedMessage ?: "Please check the network connection"))
        } catch (e: SocketException){
            emit(ResourceData.error<List<Movie>>(e.localizedMessage ?: "There is an exception occurred on Socket Connection"))
        }  catch (e: Exception) {
            emit(ResourceData.error("Something went wrong"))
        }
    }
}