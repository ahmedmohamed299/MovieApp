package banquemisr.challenge05.movieApp.presentation.movieDetails

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import banquemisr.challenge05.movieApp.common.LoadingView
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SimpleDateFormat")
@Composable
fun MovieDetailScreen(
    navController: NavController?,
    movieId: String?,
    viewModel: MovieDetailViewModel = koinViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        if (movieId != null) {
            viewModel.getDetailScreen(movieId)
        }
    }

    val stateDetails = viewModel.stateDetails.value


    val dispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher

    if (!stateDetails.isLoading) {
        val parser = SimpleDateFormat("yyyy-MM-dd")
        val formatter = SimpleDateFormat("MMMM, dd yyyy")
        var formattedDate = "N/A"
        try {
            formattedDate = (parser.parse(stateDetails.data.release_date)
                ?.let { formatter.format(it) }).toString()
        } catch (ignored: Exception) {
        }

        var runtime = "N/A"
        try {
            val hours: Int = stateDetails.data.runtime / 60
            val minutes: Int = stateDetails.data.runtime % 60

            runtime = "${hours}h ${minutes}m"
        } catch (ignored: NullPointerException) {
        }

        val genres = stateDetails.data.genres



        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Black,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White,
                    ),
                    title = {
                        Text(stateDetails.data.title)
                    }, navigationIcon = {
                        IconButton(onClick = { dispatcher.onBackPressed() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description",

                                )
                        }
                    }
                )
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(innerPadding),
                contentAlignment = Alignment.Center,

                ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LazyColumn() {
                        item {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                if (stateDetails.error.isBlank())
                                    MoviePosterView(
                                        stateDetails.data.poster_path,
                                        isDetails = true,
                                        title = stateDetails.data.title
                                    )
                            }
                        }
                        item {
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                if (stateDetails.error.isBlank()) {
                                    Spacer(modifier = Modifier.height(16.dp))
                                    TitleDescriptionView(
                                        title = "Released on",
                                        description = formattedDate.toString()
                                    )
                                    TitleDescriptionView(
                                        title = "Lasts",
                                        description = runtime,
                                        bottomMargin = 6
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    DescriptionView(description = stateDetails.data.overview)
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Spacer(modifier = Modifier.height(8.dp))
                                    GenreChipCollectionView(genres = genres)
                                    Spacer(modifier = Modifier.height(24.dp))
                                }
                            }
                        }
                    }
                }
            }
        }


    }
    if (stateDetails.error.isNotBlank()) {

        Toast.makeText(LocalContext.current, stateDetails.error, Toast.LENGTH_SHORT).show()

    }
    if (stateDetails.isLoading) {
        LoadingView()
    }

}

@Composable
@Preview
fun MovieDetailScreenPreview() {
    MovieDetailScreen(null, null)
}