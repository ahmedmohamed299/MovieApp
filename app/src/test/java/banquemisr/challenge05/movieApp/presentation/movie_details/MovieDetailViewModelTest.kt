package banquemisr.challenge05.movieApp.presentation.movie_details

import android.arch.core.executor.testing.InstantTaskExecutorRule
import banquemisr.challenge05.movieApp.MainCoroutineRule
import banquemisr.challenge05.movieApp.data.repositories.FakeMovieDetailsRepository
import banquemisr.challenge05.movieApp.domain.useCase.MovieDetailsUseCase
import banquemisr.challenge05.movieApp.presentation.movieDetails.MovieDetailViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailViewModelTest {

    private lateinit var viewModelError: MovieDetailViewModel
    private lateinit var viewModelSuccess: MovieDetailViewModel


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        viewModelError = MovieDetailViewModel(MovieDetailsUseCase(FakeMovieDetailsRepository().apply { setShouldReturnNetworkError(true) }))
        viewModelSuccess = MovieDetailViewModel(MovieDetailsUseCase(FakeMovieDetailsRepository().apply { setShouldReturnNetworkError(false) }))

    }

    @Test
    fun `error on getDetails isTrue`(){
        val result = viewModelError.stateDetails.value
        assertThat(result.error.isNotBlank()).isTrue()
    }

    @Test
    fun `success on getDetails isTrue`(){
        val result = viewModelSuccess.stateDetails.value
        assertThat(result.data.backdrop_path.isNotBlank()).isTrue()
    }

    @Test
    fun `success on getDetails and movie object has correct data isTrue`(){
        val result = viewModelSuccess.stateDetails.value
        assertThat(result.data.id == 100).isTrue()
    }


}