package com.example.notesapplofcoding.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesapplofcoding.model.Note

@Database(entities = [Note :: class],version = 1)
abstract class NotesDatabase : RoomDatabase(){

    abstract fun noteDao() : NoteDao


}