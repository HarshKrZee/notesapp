package com.example.notesapplofcoding.di

import com.example.notesapplofcoding.repository.NoteRepository
import com.example.notesapplofcoding.repository.PostRepository
import com.example.notesapplofcoding.viewmodel.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    factory { NoteRepository(get()) }
    factory { PostRepository(get()) }

    viewModel { NoteViewModel(get(),get()) }
}