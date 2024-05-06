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

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    private lateinit var dbHelper: NotesDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = NotesDBHelper(this)
        setContent {
            MainActivityLayout()
        }
    }

    @Composable
    fun MainActivityLayout() {
        val notes = remember { mutableStateOf(dbHelper.getAllNotes()) }
        val textFieldContent = remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NotesList(notes = notes.value, onDelete = { note ->
                dbHelper.deleteNote(note)
                notes.value = dbHelper.getAllNotes()
            })

            OutlinedTextField(
                value = textFieldContent.value,
                onValueChange = { textFieldContent.value = it },
                label = { Text("Enter note content") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = {
                val newNote = Note(id = notes.value.size + 1, content = textFieldContent.value)
                dbHelper.addNote(newNote)
                notes.value = dbHelper.getAllNotes()
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
}