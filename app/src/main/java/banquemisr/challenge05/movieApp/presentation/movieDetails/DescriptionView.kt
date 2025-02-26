package banquemisr.challenge05.movieApp.presentation.movieDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banquemisr.challenge05.movieApp.ui.theme.text

@Composable
fun DescriptionView(description: String, bottomMargin: Int = 12, topMargin: Int = 4) {
    Column() {
        Spacer(modifier = Modifier.height(topMargin.dp))
        Text(text = description, color = text, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(bottomMargin.dp))
    }
}

@Composable
@Preview
fun DescriptionViewPreview() {
    Column() {
        DescriptionView("After settling in Green Hills, Sonic is eager to prove he has what it takes to be a true hero. His test comes when Dr. Robotnik returns, this time with a new partner, Knuckles, in search for an emerald that has the power to destroy civilizations. Sonic teams up with his own sidekick, Tails, and together they embark on a globe-trotting journey to find the emerald before it falls into the wrong hands.")
    }
}