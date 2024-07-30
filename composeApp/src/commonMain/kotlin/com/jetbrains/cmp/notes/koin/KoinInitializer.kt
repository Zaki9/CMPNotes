package com.jetbrains.cmp.notes.koin

import com.jetbrains.cmp.notes.repository.NotesRepository
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule, platformModules)
    }
}

private val sharedModule = module {
    singleOf(::NotesRepository)
}