package banquemisr.challenge05.movieApp.presentation.movieDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banquemisr.challenge05.movieApp.ui.theme.text
import banquemisr.challenge05.movieApp.ui.theme.textRating

@Composable
fun TitleDescriptionView(title: String, description: String, bottomMargin: Int = 12){
    Column() {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = title, color = textRating, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = description, color = text, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(bottomMargin.dp))
    }
}

@Composable
@Preview
fun TitleDescriptionViewPreview(){
    Column() {
        TitleDescriptionView("Released on", "April, 23 2018")
        TitleDescriptionView("Released on", "April, 23 2018")
    }
}