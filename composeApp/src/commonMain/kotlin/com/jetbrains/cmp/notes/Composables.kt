package com.jetbrains.cmp.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jetbrains.cmp.notes.database.Note
import com.jetbrains.cmp.notes.viewmodel.NotesViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NoteEditor(modifier: Modifier = Modifier, viewmodel: NotesViewModel) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Column(modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {
        BasicTextField(
            modifier = modifier.fillMaxSize().focusRequester(focusRequester),
            value = viewmodel.notesText.value,
            onValueChange = { viewmodel.notesText.value = it },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions.Default,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
        )
    }
}


@Composable
fun NotesListScreen(
    navController: NavController, modifier: Modifier = Modifier, viewmodel: NotesViewModel,
) {
    var mNotes by remember { mutableStateOf(listOf<Note>()) }
    LaunchedEffect(Unit) {
        viewmodel.fetchNotes().collectLatest {
            mNotes = it
        }
    }
    if (mNotes.isEmpty()) {
        Box(
            modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No Notes",
                textAlign = TextAlign.Center,
                color =
                MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 14.sp
            )
        }
    }
    Column(
        modifier = modifier.padding(16.dp).verticalScroll(
            rememberScrollState()
        ), verticalArrangement = Arrangement.SpaceBetween) {
        Column {
            Spacer(modifier.size(24.dp))
            ListRow()
            Spacer(modifier.size(16.dp))
            mNotes.forEach {
                NotesCard(
                    it
                ) {
                    viewmodel.toggle()
                    navController.navigate("note_edit")
                }
                Spacer(modifier.size(16.dp))
            }
        }
    }
}

@Composable
fun ListRow() {
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
        modifier = Modifier.clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = labelText,
            textAlign = TextAlign.Center,
            color =
            MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.labelMedium,
            fontSize = 14.sp
        )
    }
}

@Composable
fun NotesCard(note: Note, launchEditScreen: (Int) -> Unit) {
    Card(
        modifier =
        Modifier.clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxWidth()
            .padding(8.dp).clickable {
                launchEditScreen.invoke(note.id)
            },
    ) {
        Column(
            Modifier
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .fillMaxWidth().padding(8.dp)

        ) {
            Text(
                text = note.noteMessage,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 24.sp,
                maxLines = 1,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = note.noteMessage,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 14.sp,
                maxLines = 3,
                fontWeight = FontWeight.Bold
            )
        }
    }
}