package com.example.notesapplofcoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.notesapplofcoding.viewmodel.NoteViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

//    val noteViewModel: NoteViewModel by inject() // this also works

     val noteViewModel: NoteViewModel by viewModel()   // this viewModel() function is from koin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}