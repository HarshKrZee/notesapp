package com.example.notesapplofcoding.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesapplofcoding.model.Note

@Database(entities = [Note :: class],version = 1)
abstract class NotesDatabase : RoomDatabase(){

//    abstract fun getNoteDao() : NoteDao

    abstract val noteDao : NoteDao

//     companion object{
//         @Volatile
//         var INSTANCE : NotesDatabase? = null
//
//         @Synchronized
//         fun getDatabaseInstance(context : Context) : NotesDatabase
//         {
//
//         }
//     }
}