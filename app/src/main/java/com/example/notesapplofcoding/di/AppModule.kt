package com.example.notesapplofcoding.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapplofcoding.db.NoteDao
import com.example.notesapplofcoding.db.NotesDatabase
import com.example.notesapplofcoding.model.Note
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getDbName(): String = "Notes"

    @Singleton
    @Provides
    fun getRoomDb(@ApplicationContext context: Context, name: String): NotesDatabase =
        Room.databaseBuilder(context, NotesDatabase::class.java,name).build()

    @Singleton
    @Provides
    fun getNoteDao(db : NotesDatabase) : NoteDao = db.noteDao
}