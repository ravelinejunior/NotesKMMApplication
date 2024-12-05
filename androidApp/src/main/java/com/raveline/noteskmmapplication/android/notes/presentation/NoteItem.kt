package com.raveline.noteskmmapplication.android.notes.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raveline.noteskmmapplication.android.MyApplicationTheme
import com.raveline.noteskmmapplication.domain.entities.Note
import com.raveline.noteskmmapplication.utils.DateTimeUtil

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    backgroundColor: Color,
    onNoteClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val formattedDate = remember(note.createdAt) {
        DateTimeUtil.formatNoteDate(note.createdAt)
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = Color.LightGray
            )
            .background(backgroundColor)
            .clickable { onNoteClick() }
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = note.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.DarkGray
            )

            Icon(
                imageVector = Icons.Default.Close,
                tint = Color.DarkGray,
                contentDescription = "Delete note",
                modifier = Modifier.clickable(
                    MutableInteractionSource(),
                    null
                ) { onDeleteClick() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = note.content,
            fontSize = 20.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = formattedDate,
            fontSize = 16.sp,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun NoteItemPreview() {
    MyApplicationTheme {
        NoteItem(
            note = Note(
                id = 1,
                title = "Note title",
                content = "Note content",
                colorHex = 0,
                createdAt = DateTimeUtil.now()
            ),
            backgroundColor = MaterialTheme.colors.surface,
            onNoteClick = {},
            onDeleteClick = {}
        )
    }

}