package com.raveline.noteskmmapplication.android.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raveline.noteskmmapplication.domain.datasource.NoteDataSource
import com.raveline.noteskmmapplication.domain.entities.Note
import com.raveline.noteskmmapplication.utils.DateTimeUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val noteTitle = savedStateHandle.getStateFlow("noteTitle", "")
    private val noteContent = savedStateHandle.getStateFlow("noteContent", "")
    private val isNoteTitleFocused = savedStateHandle.getStateFlow("noteTitleFocused", false)
    private val isNoteContentFocused = savedStateHandle.getStateFlow("noteContentFocused", false)
    private val noteColor = savedStateHandle.getStateFlow("noteColor", Note.generateRandomColor())

    val state = combine(
        noteTitle,
        noteContent,
        isNoteTitleFocused,
        isNoteContentFocused,
        noteColor
    ) { title, content, isTitleFocused, isContentFocused, color ->
        NoteDetailState(
            noteTitle = title,
            noteContent = content,
            isNoteTitleTextFocused = title.isEmpty() && !isTitleFocused,
            isNoteContentTextFocused = content.isEmpty() && !isContentFocused,
            noteColor = color
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = NoteDetailState()
    )

    private val _hasNoteBeenSaved = MutableStateFlow(false)
    val hasNoteBeenSaved = _hasNoteBeenSaved.asStateFlow()

    private var existingNoteId: Long? = null

    init {
        savedStateHandle.get<Long>("noteId")?.let { existingNoteId ->
            if (existingNoteId != -1L) {
                viewModelScope.launch {
                    noteDataSource.getNoteById(existingNoteId)?.also { note ->
                        savedStateHandle["noteTitle"] = note.title
                        savedStateHandle["noteContent"] = note.content
                        savedStateHandle["noteColor"] = note.colorHex
                        savedStateHandle["noteId"] = note.id
                    }
                }
            } else {
                return@let
            }
        }
    }

    fun onNoteTitleChange(title: String) {
        savedStateHandle["noteTitle"] = title
    }

    fun onNoteContentChange(content: String) {
        savedStateHandle["noteContent"] = content
    }

    fun onNoteTitleFocusChange(isFocused: Boolean) {
        savedStateHandle["noteTitleFocused"] = isFocused
    }

    fun onNoteContentFocusChange(isFocused: Boolean) {
        savedStateHandle["noteContentFocused"] = isFocused
    }

    fun saveNote() {
        viewModelScope.launch {
            noteDataSource.insertNote(
                Note(
                    id = existingNoteId,
                    title = noteTitle.value,
                    content = noteContent.value,
                    colorHex = noteColor.value,
                    createdAt = DateTimeUtil.now()
                )
            )
            _hasNoteBeenSaved.value = true
        }
    }
}