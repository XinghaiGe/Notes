package com.gxh.notes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotesDBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "notes.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_NOTES_TABLE = "CREATE TABLE ${NoteEntry.TABLE_NAME} (" +
                "${NoteEntry.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${NoteEntry.CONTENT} TEXT NOT NULL);"
        db.execSQL(SQL_CREATE_NOTES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${NoteEntry.TABLE_NAME}"
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun addNote(note: Note) {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(NoteEntry.CONTENT, note.content)
        }

        db.insert(NoteEntry.TABLE_NAME, null, values)

        db.close()
    }

    fun deleteNote(note: Note) {
        val db = this.writableDatabase

        val selection = "${NoteEntry.ID} LIKE ?"
        val selectionArgs = arrayOf(note.id.toString())

        db.delete(NoteEntry.TABLE_NAME, selection, selectionArgs)

        db.close()
    }

    fun getAllNotes(): List<Note> {
        val db = this.readableDatabase

        val projection = arrayOf(NoteEntry.ID, NoteEntry.CONTENT)

        val cursor = db.query(
            NoteEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val notes = mutableListOf<Note>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(NoteEntry.ID))
                val content = getString(getColumnIndexOrThrow(NoteEntry.CONTENT))
                notes.add(Note(id, content))
            }
        }

        cursor.close()
        db.close()

        return notes
    }
}