package com.raveline.noteskmmapplication.android.notes.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raveline.noteskmmapplication.domain.datasource.NoteDataSource
import com.raveline.noteskmmapplication.domain.entities.Note
import com.raveline.noteskmmapplication.domain.use_cases.SearchNotesUseCase
import com.raveline.noteskmmapplication.utils.DateTimeUtil
import com.raveline.noteskmmapplication.utils.RedOrangeHex
import com.raveline.noteskmmapplication.utils.colorList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val searchNotes = SearchNotesUseCase()

    private val notes = savedStateHandle.getStateFlow("notes", emptyList<Note>())
    private val searchText = savedStateHandle.getStateFlow("searchText", "")
    private val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)

    val state = combine(notes, searchText, isSearchActive) { notes, searchText, isActive ->
        NoteListState(
            notes = searchNotes.execute(notes, searchText),
            searchText = searchText,
            isSearchActive = isActive
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteListState())

    init {
        viewModelScope.launch {
            loadNotes()
            /*(1 .. 10).forEach {
                noteDataSource.insertNote(
                    Note(
                        id = null,
                        title = "Title $it",
                        content = "Content $it",
                        colorHex = colorList.random(),
                        createdAt = DateTimeUtil.now()
                    )
                )
            }*/
        }
    }
    fun loadNotes() {
        viewModelScope.launch {
            savedStateHandle["notes"] = noteDataSource.getAllNotes()
        }
    }

    fun onSearchTextChange(text: String) {
        savedStateHandle["isSearchActive"] = text.isNotBlank()
        savedStateHandle["searchText"] = text
    }

    fun onToggleSearch() {
        savedStateHandle["isSearchActive"] = !isSearchActive.value
        if(!isSearchActive.value) {
            savedStateHandle["searchText"] = ""
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            note.id?.let { noteDataSource.deleteNoteById(it) }
            loadNotes()
        }
    }

}