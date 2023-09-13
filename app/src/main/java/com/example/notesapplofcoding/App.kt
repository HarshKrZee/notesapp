package com.example.notesapplofcoding

import android.app.Application
import com.example.notesapplofcoding.di.retrofit_databaseBuilderModule
import com.example.notesapplofcoding.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            modules(retrofit_databaseBuilderModule, viewModelModule)
            androidContext(this@App)  // this is important
        }
    }
}