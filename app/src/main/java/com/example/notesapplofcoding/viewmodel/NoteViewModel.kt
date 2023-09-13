package com.example.notesapplofcoding.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapplofcoding.model.Note
import com.example.notesapplofcoding.model.Post
import com.example.notesapplofcoding.repository.NoteRepository
import com.example.notesapplofcoding.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel(
    val noteRepository: NoteRepository,
    val postRepository: PostRepository
) : ViewModel() {

    private val _messageFlow = MutableStateFlow<String>("")
    val messageFlow: StateFlow<String> = _messageFlow

    private val _searchNotes = MutableStateFlow<List<Note>>(emptyList())
    val searchNotes: StateFlow<List<Note>> = _searchNotes

    private val _post = MutableStateFlow<List<Post>>(emptyList())
    val post: StateFlow<List<Post>> = _post


    init {
        getNotes()
    }

    fun setMessage(message: String) {
        viewModelScope.launch { _messageFlow.emit(message) }
    }

    fun getNotes() {
        viewModelScope.launch {
            noteRepository.getNotes().collect {
                _searchNotes.emit(it)    // here emit is just like postvalue
            }
        }
    }

    fun getPost() = viewModelScope.launch {
        try {
            val posts = postRepository.getPost()
            _post.emit(posts)
            Log.d("apiResponse", posts.toString())

        } catch (e: Exception) {
            Log.d("error in apiFetching", "${e.message}")
        }
    }

    fun upsertNote(note: Note) = viewModelScope.launch {
        if (isValid(note)) {
            noteRepository.upsertNote(note)
        } else {
            _messageFlow.emit("Title and Text can't be empty")
            Log.d("fdasf", _messageFlow.value)
        }
    }

    fun deleteNote(note: Note) = viewModelScope.launch { noteRepository.deleteNote(note) }

    fun deleteAllNotes() = viewModelScope.launch { noteRepository.deleteAllNotes() }

    fun searchNotes(searchQuery: String) =
        viewModelScope.launch {
            noteRepository.searchNotes(searchQuery).collect {
                _searchNotes.emit(it)    // here emit is just like postvalue
            }
        }

    private fun isValid(note: Note): Boolean {
        return when {
            note.noteTitle.isBlank() -> false // Title must not be empty
            note.noteText.isBlank() -> false // Text must not be empty
            else -> true
        }
    }
}