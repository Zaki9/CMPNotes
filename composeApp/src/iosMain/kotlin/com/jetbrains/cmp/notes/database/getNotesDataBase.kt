package com.jetbrains.cmp.notes.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getNotesDataBase(): NoteDatabase {
    val dbFile = NSHomeDirectory() + "/note.db"
    return Room.databaseBuilder<NoteDatabase>(
        name = dbFile,
        factory = { NoteDatabase::class.instantiateImpl() }
    ).setDriver(BundledSQLiteDriver()).build()
}