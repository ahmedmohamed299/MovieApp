package banquemisr.challenge05.movieApp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import banquemisr.challenge05.movieApp.presentation.nowPlaying.PlayingNowScreen
import banquemisr.challenge05.movieApp.ui.theme.MovieAppTheme
import banquemisr.challenge05.movieApp.ui.theme.primary
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PlayingNowScreenKtTest{

    @get:Rule(order = 0)
    val composeRule = createComposeRule()

    @Before
    fun setup() {
        composeRule.setContent {
            MovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = primary
                ) {
                    val navController = rememberNavController()
                    PlayingNowScreen(navController = navController)
                }
            }
        }
    }

    @Test
    fun loading_animation_visibility(){
        composeRule.waitForIdle()

    }
}