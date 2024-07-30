import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetbrains.cmp.notes.database.Note
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            NotesHome()
        }
    }
}

@Composable
fun NotesHome() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                contentColor = Color.White
            ) {

            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(1f),
            color = Color.Black
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Notes", fontSize = 32.sp, color = Color.White)
                Spacer(Modifier.size(16.dp))
                ListRow(
                    modifier = Modifier
                )
                Spacer(Modifier.size(16.dp))
                NotesCard(
                    Note(0, "HEy this is my first notw")
                )
                Spacer(Modifier.size(16.dp))

                NotesCard(
                    Note(
                        0,
                        "HEy this is my first notwHEy this is my first notwHEy this is my first notwHEy this is my first notwHEy this is my first notwHEy this is my first notwHEy this is my first notwHEy this is my first notwHEy this is my first notwHEy this is my first notwHEy this is my first notwHEy this is my first notwHEy this is my first notwHEy this is my first notw"
                    )
                )
            }
        }
    }
}

@Composable
fun ListRow(modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Label(
            labelText = "Top 20"
        )
        Label(
            labelText = "BookMarked"
        )
        Label(
            labelText = "Important"
        )
    }
}

@Composable
fun Label(labelText: String) {
    Box(
        modifier = Modifier.clip(RoundedCornerShape(16.dp)).background(Color.White)
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = labelText,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 14.sp
        )
    }
}

@Composable
fun NotesCard(note: Note) {
    Card(
        modifier =
        Modifier.clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(8.dp),

        ) {
        Column {
            Text(
                text = note.noteMessage,
                textAlign = TextAlign.Start,
                color = Color.Black,
                fontSize = 24.sp,
                maxLines = 1,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = note.noteMessage,
                textAlign = TextAlign.Start,
                color = Color.Black,
                fontSize = 14.sp,
                maxLines = 3
            )
        }
    }
}
