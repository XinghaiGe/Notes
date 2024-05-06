package com.gxh.notes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NoteItem(note: Note, onDelete: (Note) -> Unit, onUpdate: (Note) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = note.content, modifier = Modifier.weight(1f))

        IconButton(onClick = { onUpdate(note) }) {
            Icon(Icons.Filled.Edit, contentDescription = "Update note")

        }

        IconButton(onClick = { onDelete(note) }) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete note")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNoteItem() {
    NoteItem(
        note = Note(id = 1, content = "First note"),
        onDelete = { "Delete" }, onUpdate = { "Update" }
    )
}