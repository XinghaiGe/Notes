package com.gxh.notes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NotesList(notes: List<Note>, onDelete: (Note) -> Unit, onUpdate: (Note) -> Unit) {
    if (notes.isEmpty()) {
        Text(text = "No notes to display.")
    } else {
        LazyColumn {
            items(notes) { note ->
                NoteItem(note = note, onDelete = onDelete, onUpdate = onUpdate)
            }
        }
    }
}

@Composable
fun NotesList(notes: List<Note>) {
    LazyColumn {
        items(notes) { note ->
            Text(text = note.content)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNotesList() {
    val notes = listOf(
        Note(id = 1, content = "First note"),
        Note(id = 2, content = "Second note"),
        Note(id = 3, content = "Third note")
    )
    NotesList(notes)
}