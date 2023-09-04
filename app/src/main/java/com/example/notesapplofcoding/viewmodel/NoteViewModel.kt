package com.example.notesapplofcoding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapplofcoding.model.Note
import com.example.notesapplofcoding.model.Post
import com.example.notesapplofcoding.repository.NoteRepository
import com.example.notesapplofcoding.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    val noteRepository: NoteRepository,
    val postRepository: PostRepository
) : ViewModel() {

    val notes = noteRepository.getNotes()
    private val _searchNotes = MutableStateFlow<List<Note>>(emptyList())
    val searchNotes: StateFlow<List<Note>> = _searchNotes

    private val _post  = MutableStateFlow<List<Post>>(emptyList())
    val post  : StateFlow<List<Post>> = _post

//    fun getPostLiveData() : LiveData<List<Post>> = post

    fun getPost() = viewModelScope.launch {
        val posts =   postRepository.getPost()
        _post.emit(posts)
    }

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