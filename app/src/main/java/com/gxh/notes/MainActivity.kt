package com.gxh.notes

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.util.Log
import com.gxh.notes.Note
import com.gxh.notes.NotesList

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityLayout()
        }
    }
}

@Composable
fun MainActivityLayout() {
    // Create a list of notes in your application state
    val notes = remember {
        mutableStateOf(
            mutableListOf(
                Note(id = 1, content = "First note"),
                Note(id = 2, content = "Second note"),
                Note(id = 3, content = "Third note")
            )
        )
    }

    // Create a state for the TextField content
    val textFieldContent = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Replace the Text Composable with your NotesList
        NotesList(notes = notes.value, onDelete = { note ->
            notes.value = notes.value.filter { it.id != note.id }.toMutableList()
        })

        // Add a TextField for note input
        OutlinedTextField(
            value = textFieldContent.value,
            onValueChange = { textFieldContent.value = it },
            label = { Text("Enter note content") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = {
            Log.d("MainActivityLayout", "Add Note button clicked")
            // Add a new note to the list when the button is clicked
            val newNote = Note(id = notes.value.size + 1, content = textFieldContent.value)
            notes.value = (notes.value + newNote).toMutableList()
            // Clear the TextField content
            textFieldContent.value = ""
        }) {
            Text(text = "Add Note")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivityLayout() {
    MainActivityLayout()
}