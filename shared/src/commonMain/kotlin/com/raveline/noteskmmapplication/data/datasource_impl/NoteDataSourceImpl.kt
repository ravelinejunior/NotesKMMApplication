package com.raveline.noteskmmapplication.data.datasource_impl

import com.raveline.noteskmmapplication.database.NotesDatabase
import com.raveline.noteskmmapplication.domain.datasource.NoteDataSource
import com.raveline.noteskmmapplication.domain.entities.Note
import com.raveline.noteskmmapplication.domain.entities.toNote
import com.raveline.noteskmmapplication.utils.DateTimeUtil

class NoteDataSourceImpl(
    db: NotesDatabase
) : NoteDataSource {
    private val queries = db.notesQueries

    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            id = note.id,
            title = note.title,
            content = note.content,
            colorHex = note.colorHex,
            createdAt = DateTimeUtil.toEpochMillis(note.createdAt)
        )
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries.getNoteById(id).executeAsOneOrNull()?.toNote()
    }

    override suspend fun getAllNotes(): List<Note> {
        return queries.getAllNotes().executeAsList().map {
            it.toNote()
        }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNote(id)
    }

    override suspend fun updateNote(note: Note) {
        note.id?.let {
            queries.updateNote(
                title = note.title,
                content = note.content,
                colorHex = note.colorHex,
                createdAt = DateTimeUtil.toEpochMillis(note.createdAt),
                id = it
            )
        }
    }
}