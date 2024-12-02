package com.raveline.noteskmmapplication.android.details

import androidx.compose.ui.graphics.Color

data class NoteDetailState(
    val noteTitle: String = "",
    val noteContent: String = "",
    val noteColor: Long = 0L,
    val isNoteTitleTextFocused: Boolean = false,
    val isNoteContentTextFocused: Boolean = false
)