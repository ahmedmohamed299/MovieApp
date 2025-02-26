package banquemisr.challenge05.movieApp.presentation.playing_now

import android.arch.core.executor.testing.InstantTaskExecutorRule
import banquemisr.challenge05.movieApp.MainCoroutineRule
import banquemisr.challenge05.movieApp.domain.useCase.NowPlayingUseCase
import banquemisr.challenge05.movieApp.presentation.nowPlaying.MovieViewModel
import banquemisr.challenge05.movieApp.data.repositories.FakeMovieRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PlayingNowViewModelTest{

    private lateinit var viewModelError: MovieViewModel
    private lateinit var viewModelSuccess: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup(){
        viewModelError = MovieViewModel(NowPlayingUseCase(FakeMovieRepository().apply {setShouldReturnNetworkError(true)}))
        viewModelSuccess = MovieViewModel(NowPlayingUseCase(FakeMovieRepository().apply {setShouldReturnNetworkError(false)}))
    }

    @Test
    fun `error on getNowPlayingMovies isTrue`(){
        val result = viewModelError.nowPlayingState.value
        assertThat(result.error.isNotBlank()).isTrue()
    }

    @Test
    fun `success on getNowPlayingMovies isTrue`(){
        val result = viewModelSuccess.nowPlayingState.value
        assertThat(result.movies.isNotEmpty()).isTrue()
    }

    @Test
    fun `success on getNowPlayingMovies and movie object two present isTrue`(){
        val result = viewModelSuccess.nowPlayingState.value
        assertThat(result.movies.size>2).isTrue()
        assertThat(result.movies[1].original_title == "title1").isTrue()
    }
}