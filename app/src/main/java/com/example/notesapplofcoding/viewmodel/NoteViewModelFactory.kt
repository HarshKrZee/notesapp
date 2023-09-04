package com.example.notesapplofcoding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapplofcoding.repository.NoteRepository
import com.example.notesapplofcoding.repository.PostRepository

class NoteViewModelFactory (private val noteRepository: NoteRepository,private val postRepository: PostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return NoteViewModel(noteRepository,postRepository) as T
    }
}