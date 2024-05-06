package com.gxh.notes

import android.provider.BaseColumns

object NoteEntry : BaseColumns {
    const val TABLE_NAME = "notes"
    const val ID = BaseColumns._ID
    const val CONTENT = "content"
}