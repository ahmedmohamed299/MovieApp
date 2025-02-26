package banquemisr.challenge05.movieApp.common

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsProperties.TestTag
import banquemisr.challenge05.movieApp.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LoadingView() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.searching))
    val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)
    Column(modifier = Modifier.testTag("loading")) {
        LottieAnimation(
            composition,
            progress,
        )
    }
}