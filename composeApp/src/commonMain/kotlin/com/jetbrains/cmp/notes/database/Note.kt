package com.jetbrains.cmp.notes.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val noteMessage: String,
    var markImp: Boolean = false,
    val timeStamp: String
)