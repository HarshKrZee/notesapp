package com.example.notesapplofcoding.repository

import com.example.notesapplofcoding.db.NoteDao
import com.example.notesapplofcoding.model.Note

class NoteRepository (private val noteDao : NoteDao) {

    suspend fun upsertNote(note : Note) = noteDao.upsertNote(note)

    suspend fun deleteNote(note : Note) = noteDao.deleteNote(note)

    fun getNotes() = noteDao.selectNotes()

    fun searchNotes(searchQuery : String) = noteDao.searchInNotesTitle(searchQuery)

    suspend fun deleteAllNotes() = noteDao.deleteAllNotes()

}