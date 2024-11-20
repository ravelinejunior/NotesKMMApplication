package com.raveline.noteskmmapplication.domain.datasource

import com.raveline.noteskmmapplication.domain.entities.Note

interface NoteDataSource {
    suspend fun insertNote(note: Note)
    suspend fun getNoteById(id: Long): Note?
    suspend fun getAllNotes(): List<Note>
    suspend fun deleteNoteById(id: Long)
    suspend fun updateNote(note: Note)
}