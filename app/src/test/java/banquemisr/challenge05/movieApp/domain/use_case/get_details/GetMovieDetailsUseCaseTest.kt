package banquemisr.challenge05.movieApp.domain.use_case.get_details

import app.cash.turbine.test
import banquemisr.challenge05.movieApp.common.ResourceData
import banquemisr.challenge05.movieApp.data.repositories.FakeMovieDetailsRepository
import banquemisr.challenge05.movieApp.domain.useCase.MovieDetailsUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetMovieDetailsUseCaseTest{
    private lateinit var useCaseError: MovieDetailsUseCase
    private lateinit var useCaseSuccess: MovieDetailsUseCase

    private val id = "100"

    @Before
    fun setup(){
        useCaseError = MovieDetailsUseCase(FakeMovieDetailsRepository().apply { setShouldReturnNetworkError(true) })
        useCaseSuccess = MovieDetailsUseCase(FakeMovieDetailsRepository().apply { setShouldReturnNetworkError(false) })
    }

    @Test
    fun `verify state change from loading to error on API or any error`() = runBlocking{
        useCaseError.invoke(id).test {
            val emitLoading = awaitItem()
            assertThat(emitLoading).isInstanceOf(ResourceData.loading::class.java)

            val emitError = awaitItem()
            assertThat(emitError).isInstanceOf(ResourceData.error::class.java)

            awaitComplete()
        }
    }

    @Test
    fun `verify state change from loading to success on API success`() = runBlocking{
        useCaseSuccess.invoke(id).test {
            val emitLoading = awaitItem()
            assertThat(emitLoading).isInstanceOf(ResourceData.loading::class.java)

            val emitSuccess = awaitItem()
            assertThat(emitSuccess).isInstanceOf(ResourceData.success::class.java)

            awaitComplete()
        }
    }

    @Test
    fun `verify API success return movie details`() = runBlocking{
        useCaseSuccess.invoke(id).test {
            val emitLoading = awaitItem()
            assertThat(emitLoading).isInstanceOf(ResourceData.loading::class.java)

            val emitSuccess = awaitItem()
            assertThat(emitSuccess).isInstanceOf(ResourceData.success::class.java)

            assertThat(emitSuccess.data!!.id == 100).isTrue()

            awaitComplete()
        }
    }
}