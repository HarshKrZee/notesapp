package com.example.notesapplofcoding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesapplofcoding.screens.Detail
import com.example.notesapplofcoding.screens.NotesScreen
import com.example.notesapplofcoding.screens.PostsScreen
import com.example.notesapplofcoding.viewmodel.NoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }

    @Composable
    fun App() {
        val noteViewModel: NoteViewModel by viewModel()
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "notes")
        {
            composable(route = "notes")
            {
                NotesScreen(noteViewModel, navController)
            }
            composable(
                route = "details?id={id}&noteText={noteText}&noteTitle={noteTitle}",
                arguments = listOf(navArgument("noteTitle") {
                    type = NavType.StringType
                }, navArgument("noteText") { type = NavType.StringType })
            )
            {
                val id = it.arguments!!.getString("id")?.toInt()
                val text = it.arguments!!.getString("noteText")
                val title = it.arguments!!.getString("noteTitle")
                Detail(noteViewModel, id!!, text!!, title!!, navController)
            }
            composable(route = "posts")
            {
                PostsScreen(noteViewModel, navController)
            }
        }
    }
}
