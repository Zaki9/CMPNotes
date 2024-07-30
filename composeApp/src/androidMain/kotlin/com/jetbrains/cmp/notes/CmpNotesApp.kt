package com.jetbrains.cmp.notes

import android.app.Application
import com.jetbrains.cmp.notes.koin.initKoin
import org.koin.android.ext.koin.androidContext

class CmpNotesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@CmpNotesApp)
        }
    }
}