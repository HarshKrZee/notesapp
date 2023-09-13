package com.example.notesapplofcoding.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapplofcoding.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface  NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(note : Note)

    @Delete
    suspend fun deleteNote(note : Note)

    @Query("SELECT * from Note ORDER BY noteId DESC")
    fun selectNotes() : Flow<List<Note>>

    @Query("SELECT * from Note WHERE noteTitle like '%'||:searchQuery||'%'")
    fun searchInNotesTitle(searchQuery: String) : Flow<List<Note>>

    @Query("DELETE FROM Note")
    suspend fun deleteAllNotes()




}