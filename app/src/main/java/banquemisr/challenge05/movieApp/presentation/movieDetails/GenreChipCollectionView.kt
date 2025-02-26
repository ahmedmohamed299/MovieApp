package banquemisr.challenge05.movieApp.presentation.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banquemisr.challenge05.movieApp.domain.model.movieDetails.Genre
import banquemisr.challenge05.movieApp.ui.theme.genre
import banquemisr.challenge05.movieApp.ui.theme.genreText
import com.google.accompanist.flowlayout.FlowRow
import java.util.Locale

@Composable
fun GenreChipCollectionView(genres: List<Genre>?) {
    FlowRow(mainAxisSpacing = 8.dp, crossAxisSpacing = 8.dp, modifier = Modifier.fillMaxWidth()) {
        genres?.forEach { item ->
            GenreChipView(item.name)
        }
    }
}

/**
 * GenreChip view will act as a text holder for type of genre
 *
 * @param lan will be the text
 */
@Composable
fun GenreChipView(lan: String) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.background(genre)
    ) {
        Row(Modifier.padding(6.dp)) {
            Text(text = lan.uppercase(Locale.getDefault()), color = genreText, textAlign = TextAlign.Center, fontSize = 14.sp)
        }
    }
}

@Composable
@Preview
fun GenreChipCollectionViewPreview() {
    GenreChipCollectionView(
        listOf(
            Genre(name = "Biography"),
            Genre(name = "Drama"),
            Genre(name = "Music")
        )
    )
}