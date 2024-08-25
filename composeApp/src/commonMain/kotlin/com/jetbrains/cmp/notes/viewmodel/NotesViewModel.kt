package com.jetbrains.cmp.notes.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.cmp.notes.database.Note
import com.jetbrains.cmp.notes.repository.NotesRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class NotesViewModel(private val notesRepository: NotesRepository) : ViewModel() {
    val toggleToolbar = mutableStateOf(true)
    var notesText = mutableStateOf("")
    var notesList = mutableStateOf(listOf<Note>())

    fun toggle() {
        toggleToolbar.value = !toggleToolbar.value
    }
    fun fetchNotes() {
        viewModelScope.launch {
            notesRepository.fetchFromRepo().collectLatest {
                notesList.value = it
            }
        }
    }

    fun fetchImpNotes() {
        viewModelScope.launch {
            notesRepository.fetchImpNotes().collectLatest {
                notesList.value = it
            }
        }
    }
    fun addNotes() {
        viewModelScope.launch {
            if (notesText.value.isNotBlank()) {
                notesRepository.addNotes(
                    Note(
                        noteMessage = (notesText.value),
                        timeStamp = Clock.System.now().toString()
                    )
                )
                notesText.value = ""
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            notesRepository.deleteNote(note)
        }
    }

    fun markImportant(note: Note) {
        viewModelScope.launch {
            notesRepository.addNotes(note.apply { markImp = !markImp })
        }
    }
}