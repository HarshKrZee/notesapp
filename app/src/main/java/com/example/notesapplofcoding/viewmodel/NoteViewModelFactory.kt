package com.example.notesapplofcoding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapplofcoding.repository.NoteRepository
import javax.inject.Inject

class NoteViewModelFactory @Inject constructor(private val noteRepository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return NoteViewModel(noteRepository) as T
    }
}