package com.jetbrains.cmp.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.cmp.notes.database.Note
import com.jetbrains.cmp.notes.repository.NotesRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NotesViewModel(private val notesRepository: NotesRepository) : ViewModel() {
    init {
        viewModelScope.launch {
            addNotes()
            notesRepository.fetchFromRepo().collectLatest {

                println("sizeeee of mine is".plus(it.size))
            }
        }
    }

    suspend fun addNotes() {
        notesRepository.addNotes(
            Note(
                noteMessage = "Hey man"
            )
        )
    }
}