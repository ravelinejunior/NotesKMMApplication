package com.raveline.noteskmmapplication.android.notes.presentation

import com.raveline.noteskmmapplication.domain.entities.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)