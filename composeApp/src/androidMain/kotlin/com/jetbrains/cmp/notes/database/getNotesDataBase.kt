package com.jetbrains.cmp.notes.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

fun getNotesDataBase(context: Context): NoteDatabase {
    val dbFile = context.getDatabasePath("note.db")
    return Room.databaseBuilder<NoteDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    ).setDriver(BundledSQLiteDriver()).build()
}