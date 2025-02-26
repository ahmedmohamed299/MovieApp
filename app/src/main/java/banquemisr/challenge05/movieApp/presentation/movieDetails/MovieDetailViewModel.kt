package banquemisr.challenge05.movieApp.presentation.movieDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banquemisr.challenge05.movieApp.common.ResourceData
import banquemisr.challenge05.movieApp.domain.useCase.MovieDetailsUseCase
import banquemisr.challenge05.movieApp.domain.model.movieDetails.MovieDetail
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MovieDetailViewModel(private val useCase: MovieDetailsUseCase) : ViewModel() {

    private val _stateDetails = mutableStateOf<MovieDetailState>(MovieDetailState())
    val stateDetails: State<MovieDetailState> = _stateDetails


    fun getDetailScreen(id: String) {

        useCase(id).onEach { result ->
            when (result) {
                is ResourceData.success -> {
                    _stateDetails.value =
                        MovieDetailState(isLoading = false, data = result.data ?: MovieDetail())
                }

                is ResourceData.loading -> {
                    _stateDetails.value = MovieDetailState(isLoading = true)
                }

                is ResourceData.error -> {
                    _stateDetails.value =
                        MovieDetailState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)


    }
}