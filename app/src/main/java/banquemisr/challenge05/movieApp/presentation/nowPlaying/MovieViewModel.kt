package banquemisr.challenge05.movieApp.presentation.nowPlaying

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banquemisr.challenge05.movieApp.common.Constants
import banquemisr.challenge05.movieApp.common.ResourceData
import banquemisr.challenge05.movieApp.domain.useCase.NowPlayingUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MovieViewModel(private val useCase: NowPlayingUseCase) : ViewModel(){
    private val _nowPlayingState = mutableStateOf<PlayingNowListState>(PlayingNowListState())
    val nowPlayingState: State<PlayingNowListState> = _nowPlayingState

    private val _upcomingState = mutableStateOf<PlayingNowListState>(PlayingNowListState())
    val upcomingState: State<PlayingNowListState> = _upcomingState

    private val _popularState = mutableStateOf<PlayingNowListState>(PlayingNowListState())
    val popularState: State<PlayingNowListState> = _popularState

    init {
        getPlayingNowMovies()
        getUpcomingMovies()
        getPopularMovies()
    }


    private fun getPlayingNowMovies(){
        useCase(Constants.NOW_PLAYING).onEach { result ->
            when(result){
                is ResourceData.success ->{
                    _nowPlayingState.value = PlayingNowListState(isLoading = false, movies = result.data ?: emptyList())
                }
                is ResourceData.loading ->{
                    _nowPlayingState.value = PlayingNowListState(isLoading = true)
                }
                is ResourceData.error ->{
                    _nowPlayingState.value = PlayingNowListState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getUpcomingMovies(){
        useCase(Constants.UPCOMING).onEach { result ->
            when(result){
                is ResourceData.success ->{
                    _upcomingState.value = PlayingNowListState(isLoading = false, movies = result.data ?: emptyList())
                }
                is ResourceData.loading ->{
                    _upcomingState.value = PlayingNowListState(isLoading = true)
                }
                is ResourceData.error ->{
                    _upcomingState.value = PlayingNowListState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getPopularMovies(){
        useCase(Constants.POPULAR).onEach { result ->
            when(result){
                is ResourceData.success ->{
                    _popularState.value = PlayingNowListState(isLoading = false, movies = result.data ?: emptyList())
                }
                is ResourceData.loading ->{
                    _popularState.value = PlayingNowListState(isLoading = true)
                }
                is ResourceData.error ->{
                    _popularState.value = PlayingNowListState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}