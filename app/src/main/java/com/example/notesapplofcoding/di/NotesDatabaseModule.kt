package com.example.notesapplofcoding.di

import android.app.Application
import androidx.room.Room
import com.example.notesapplofcoding.db.NoteDao
import com.example.notesapplofcoding.db.NotesDatabase
import org.koin.dsl.module

fun providesDatabase(application: Application): NotesDatabase =
    Room.databaseBuilder(application, NotesDatabase::class.java, "notesDatabase").build()


fun provideDao(db : NotesDatabase) : NoteDao = db.noteDao()

val notesDatabaseModule = module {

    single { providesDatabase(get()) }
    single { provideDao(get()) }
}