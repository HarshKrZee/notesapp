package com.example.notesapplofcoding.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapplofcoding.model.Post
import com.example.notesapplofcoding.viewmodel.NoteViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostsScreen(noteViewModel: NoteViewModel, navController: NavController) {

    val posts = noteViewModel.post.collectAsState(listOf())
    val message =  noteViewModel.messageFlow.collectAsState()
    val context = LocalContext.current

    Column {
        if (message.value != "") {
            Toast.makeText(context, message.value, Toast.LENGTH_SHORT).show()
            noteViewModel.setMessage("")
        }
        PostItems(
            posts.value, navController
        )
    }

}

@Composable
fun PostItems(notes: List<Post>, navController: NavController) {
    LazyColumn {
        items(notes) { item ->
            PostItem(item, navController)
        }
    }
}

@Composable
fun PostItem(item: Post, navController: NavController) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .padding(10.dp, 6.dp)
            .clickable {},
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = item.body, modifier = Modifier
                    .background(Color.White)
                    .padding(20.dp)
                    .weight(.8f)
            )
        }

    }
}