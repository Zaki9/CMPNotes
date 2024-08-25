package com.jetbrains.cmp.notes.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Upsert
    suspend fun upsert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * from Note where markImp = false ORDER BY timeStamp desc")
    fun fetchAllNotes(): Flow<List<Note>>

    @Query("SELECT * from Note where markImp = true ORDER BY timeStamp desc")
    fun fetchImpNotes(): Flow<List<Note>>
}