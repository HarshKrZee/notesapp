package com.example.notesapplofcoding.screens


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapplofcoding.model.Note
import com.example.notesapplofcoding.viewmodel.NoteViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(noteViewModel: NoteViewModel) {
    var state = remember {
        mutableStateOf("")
    }

    TextField(
        value = state.value,
        onValueChange = {
            state.value = it
            Log.d("search", "safdas")
            noteViewModel.searchNotes(it.trim())
        },
        label = { Text("Search") },
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .padding(5.dp)
    )
}

@Composable
fun FloatingButtons(navController: NavController, noteViewModel: NoteViewModel) {

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    )
    {
        ExtendedFloatingActionButton(
            onClick = { navController.navigate("details?id=0&noteText=&noteTitle=") },
            icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
            text = { Text(text = "Add Note") },
        )

        ExtendedFloatingActionButton(
            onClick = {
                noteViewModel.getPost()
                navController.navigate("posts")
            },
            icon = { Icon(Icons.Filled.Info, "Extended floating action button.") },
            text = { Text(text = "Get Posts") },
        )
    }

}

@Composable
fun EditButton(navController: NavController, item: Note) {
    SmallFloatingActionButton(
        onClick = {
            navController.navigate("details?id=${item.noteId}&noteText=${item.noteText}&noteTitle=${item.noteTitle}")
        },
    ) {
        Icon(Icons.Filled.Edit, "Floating action button.")
    }
}

@Composable
fun DeleteButton(noteViewModel: NoteViewModel, item: Note) {
    SmallFloatingActionButton(
        onClick = { noteViewModel.deleteNote(item) },
    ) {
        Icon(Icons.Filled.Delete, "Floating action button.")
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(noteViewModel: NoteViewModel, navController: NavController) {

    val notes = noteViewModel.searchNotes.collectAsState(listOf())

    Scaffold(floatingActionButton = { FloatingButtons(navController, noteViewModel) }) {
        Column {
            SearchBox(noteViewModel)
            NotesItems(
                notes.value, navController, noteViewModel
            )
        }

    }

}


@Composable
fun NotesItems(notes: List<Note>, navController: NavController, noteViewModel: NoteViewModel) {
    LazyColumn {
        items(notes) { item ->
            NotesItem(item, navController, noteViewModel)
        }
    }
}

@Composable
fun NotesItem(item: Note, navController: NavController, noteViewModel: NoteViewModel) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .padding(10.dp, 6.dp)
            .clickable {
                navController.navigate("details?id=${item.noteId}&noteText=${item.noteText}&noteTitle=${item.noteTitle}")
            },
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = item.noteTitle, modifier = Modifier
                    .background(Color.White)
                    .padding(20.dp)
                    .weight(.8f)
            )
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(.3f)
            )
            {
                EditButton(navController, item)
                DeleteButton(noteViewModel, item)
            }
        }

    }
}