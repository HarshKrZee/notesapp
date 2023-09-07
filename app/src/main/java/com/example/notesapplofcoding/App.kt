package com.example.notesapplofcoding

import android.app.Application
import com.example.notesapplofcoding.di.notesDatabaseModule
import com.example.notesapplofcoding.di.retrofitBuilderModule
import com.example.notesapplofcoding.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            modules(retrofitBuilderModule, notesDatabaseModule, viewModelModule)
            androidContext(this@App)  // this is important
        }
    }
}