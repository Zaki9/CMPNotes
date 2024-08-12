package com.jetbrains.cmp.notes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.cmp.notes.database.Note
import com.jetbrains.cmp.notes.repository.NotesRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NotesViewModel(private val notesRepository: NotesRepository) : ViewModel() {
    val toggleToolbar = mutableStateOf(true)
    var notesText = mutableStateOf("")

    fun toggle() {
        toggleToolbar.value = !toggleToolbar.value
    }

    suspend fun fetchNotes() = notesRepository.fetchFromRepo()
    fun addNotes() {
        viewModelScope.launch {
            if (notesText.value.isNotBlank()) {
                notesRepository.addNotes(
                    Note(
                        noteMessage = (notesText.value)
                    )
                )
                notesText.value = ""
            }
        }
    }
}