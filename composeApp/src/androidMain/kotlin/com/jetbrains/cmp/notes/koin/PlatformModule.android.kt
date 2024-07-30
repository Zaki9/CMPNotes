package com.jetbrains.cmp.notes.koin

import com.jetbrains.cmp.notes.database.getNotesDataBase
import com.jetbrains.cmp.notes.viewmodel.NotesViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

actual val platformModules = module {
    singleOf(::getNotesDataBase)
    viewModelOf(::NotesViewModel)
}