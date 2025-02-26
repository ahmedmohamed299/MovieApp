package banquemisr.challenge05.movieApp.domain.use_case.get_playing_now

import android.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import banquemisr.challenge05.movieApp.common.ResourceData
import banquemisr.challenge05.movieApp.data.repositories.FakeMovieRepository
import banquemisr.challenge05.movieApp.domain.useCase.NowPlayingUseCase
import com.google.common.truth.Truth.assertThat

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetNowPlayingMoviesUseCaseTest {

    private lateinit var useCaseError: NowPlayingUseCase
    private lateinit var useCaseSuccess: NowPlayingUseCase
    private val kind = "mow_playing"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        useCaseError = NowPlayingUseCase(FakeMovieRepository().apply {
            setShouldReturnNetworkError(value = true)
        })
        useCaseSuccess = NowPlayingUseCase(FakeMovieRepository().apply {
            setShouldReturnNetworkError(value = false)
        })
    }

    @Test
    fun `verify state change from loading to error on API or any error`() = runBlocking{
        useCaseError.invoke(kind).test {
            val emitLoading = awaitItem()
            assertThat(emitLoading).isInstanceOf(ResourceData.loading::class.java)

            val emitError = awaitItem()
            assertThat(emitError).isInstanceOf(ResourceData.error::class.java)

            awaitComplete()
        }
    }

    @Test
    fun `verify state change from loading to success on API success`() = runBlocking{
        useCaseSuccess.invoke(kind).test {
            val emitLoading = awaitItem()
            assertThat(emitLoading).isInstanceOf(ResourceData.loading::class.java)

            val emitSuccess = awaitItem()
            assertThat(emitSuccess).isInstanceOf(ResourceData.success::class.java)

            awaitComplete()
        }
    }

    @Test
    fun `verify API success return movie list`() = runBlocking{
        useCaseSuccess.invoke(kind).test {
            val emitLoading = awaitItem()
            assertThat(emitLoading).isInstanceOf(ResourceData.loading::class.java)

            val emitSuccess = awaitItem()
            assertThat(emitSuccess).isInstanceOf(ResourceData.success::class.java)

            assertThat(emitSuccess.data!!.size>2).isTrue()

            awaitComplete()
        }
    }
}