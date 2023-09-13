package com.example.notesapplofcoding.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapplofcoding.model.Note
import com.example.notesapplofcoding.viewmodel.NoteViewModel


@Composable
fun SaveButton( saveNote: () -> Unit) {

    FloatingActionButton(
        onClick = { saveNote() },
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Detail(
    noteViewModel: NoteViewModel,
    id: Int = 0,
    text: String,
    title: String,
    navController: NavController
) {
    val text = remember { mutableStateOf(text) }
    val title = remember { mutableStateOf(title) }

    val message =  noteViewModel.messageFlow.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(message) {
        if (message.value != "") {
            // Show a toast when the message changes
            Toast.makeText(context, message.value, Toast.LENGTH_SHORT).show()
            // Optionally, reset the message to null after showing the toast
            noteViewModel.setMessage("")
        }
    }

    Scaffold(floatingActionButton = {
        SaveButton() {
            noteViewModel.upsertNote(Note(id, title.value, text.value))
            navController.navigate("notes")
            Log.d("title1", "${title.value}")
        }
    }) {
        Column {
            DetailItem(title.value, "title") { str: String ->
                Log.d("title1", "${title.value}")
                title.value = str
            }
            DetailItem(text.value, "text") { str: String ->
                Log.d("title2", "${text.value}")
                text.value = str
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailItem(text: String, type: String, changeText: (String) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = text,
            onValueChange = {
                changeText(it)
            },
            modifier = Modifier
                .background(Color.White)
                .padding(12.dp)
                .fillMaxWidth(),
            label = { Text(type) },
        )
    }
}