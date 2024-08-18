package com.jetbrains.cmp.notes.repository

import com.jetbrains.cmp.notes.database.Note
import com.jetbrains.cmp.notes.database.NoteDatabase

class NotesRepository(private val notesDataBase: NoteDatabase) {
    fun fetchFromRepo() = notesDataBase.notesDao().fetchAllNotes()
    suspend fun addNotes(note: Note) = notesDataBase.notesDao().upsert(note)

    suspend fun deleteNote(note: Note) = notesDataBase.notesDao().delete(note)
}