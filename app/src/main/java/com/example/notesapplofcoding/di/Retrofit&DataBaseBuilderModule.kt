package com.example.notesapplofcoding.di

import android.app.Application
import androidx.room.Room
import com.example.notesapplofcoding.BuildConfig
import com.example.notesapplofcoding.api.ApiInterface
import com.example.notesapplofcoding.db.NoteDao
import com.example.notesapplofcoding.db.NotesDatabase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

fun provideApiService(): ApiInterface =
    Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create()

fun providesDatabase(application: Application): NotesDatabase =
    Room.databaseBuilder(application, NotesDatabase::class.java, "notesDatabase").build()


fun provideDao(db : NotesDatabase) : NoteDao = db.noteDao()

val retrofit_databaseBuilderModule = module {
    single{ provideApiService() }
    single { providesDatabase(get()) }
    single { provideDao(get()) }
}