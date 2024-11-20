package com.raveline.noteskmmapplication.domain.entities

import database.NoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        colorHex = colorHex,
        createdAt = Instant.fromEpochMilliseconds(
            createdAt
        ).toLocalDateTime(TimeZone.currentSystemDefault())
    )
}