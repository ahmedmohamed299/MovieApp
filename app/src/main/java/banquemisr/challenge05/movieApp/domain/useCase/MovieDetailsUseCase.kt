package banquemisr.challenge05.movieApp.domain.useCase

import banquemisr.challenge05.movieApp.common.ResourceData
import banquemisr.challenge05.movieApp.data.model.toMovieDetails
import banquemisr.challenge05.movieApp.domain.model.movieDetails.MovieDetail
import banquemisr.challenge05.movieApp.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException

class MovieDetailsUseCase(private val repository: MovieDetailsRepository) {
    operator  fun invoke(id: String): Flow<ResourceData<MovieDetail>> = flow {
        emit(ResourceData.loading())

        try {

            emit(ResourceData.success(repository.getMoviesDetails(id).toMovieDetails()))
        }catch (e: HttpException){
            emit(ResourceData.error<MovieDetail>(e.localizedMessage ?: "There is an exception occurred on HTTP Connection"))
        } catch (e: IOException){
            emit(ResourceData.error<MovieDetail>(e.localizedMessage ?: "Please check the network connection"))
        } catch (e: SocketException){
            emit(ResourceData.error<MovieDetail>(e.localizedMessage ?: "There is an exception occurred on Socket Connection"))
        }  catch (e: Exception) {
            emit(ResourceData.error("Something went wrong"))
        }
    }
}