package com.example.notesapplofcoding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapplofcoding.model.Note
import com.example.notesapplofcoding.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(val noteRepository: NoteRepository) : ViewModel() {

    val notes = noteRepository.getNotes()
    private val _searchNotes = MutableStateFlow<List<Note>>(emptyList())
    val searchNotes : StateFlow<List<Note>> = _searchNotes

    fun upsertNote(note: Note) = viewModelScope.launch { noteRepository.upsertNote(note) }

    fun deleteNote(note: Note) = viewModelScope.launch { noteRepository.deleteNote(note) }

    fun deleteAllNotes() = viewModelScope.launch { noteRepository.deleteAllNotes() }

    fun searchNotes(searchQuery: String) =
        viewModelScope.launch {
            noteRepository.searchNotes(searchQuery).collect {
                _searchNotes.emit(it)    // here emit is just like postvalue
            }
        }
}