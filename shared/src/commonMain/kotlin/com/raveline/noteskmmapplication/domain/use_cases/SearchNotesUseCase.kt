package com.raveline.noteskmmapplication.domain.use_cases

import com.raveline.noteskmmapplication.domain.entities.Note
import com.raveline.noteskmmapplication.utils.DateTimeUtil

class SearchNotesUseCase {

    fun execute(notes: List<Note>, query: String): List<Note> {
        if (query.isBlank()) return notes
        return notes.filter {
            it.title.contains(query.trim(), ignoreCase = true) ||
                    it.content.contains(query.trim(), ignoreCase = true)
        }.sortedBy {
            DateTimeUtil.toEpochMillis(it.createdAt)
        }
    }
}