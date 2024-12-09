package com.raveline.noteskmmapplication.android.details

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.raveline.noteskmmapplication.android.MyApplicationTheme
import com.raveline.noteskmmapplication.android.details.components.TransparentHintTextField

@Composable
fun NotesDetailScreen(
    noteId: Long,
    viewModel: NoteDetailViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by viewModel.state.collectAsState()
    val hasNoteBeenSaved by viewModel.hasNoteBeenSaved.collectAsState()

    LaunchedEffect(key1 = hasNoteBeenSaved) {
        if (hasNoteBeenSaved) {
            navController.popBackStack()
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = viewModel::upsertNote,
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Check note")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(Color(state.noteColor))
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            TransparentHintTextField(
                text = state.noteTitle,
                hint = "Enter a title ...",
                isHintVisible = state.isNoteTitleTextFocused,
                singleLine = true,
                textStyle = MaterialTheme.typography.h3.copy(
                    fontSize = 20.sp,
                ),
                onValueChange = viewModel::onNoteTitleChange,
                onFocusChanged = {
                    viewModel.onNoteTitleFocusChange(it.isFocused)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = state.noteContent,
                hint = "Enter the content ...",
                isHintVisible = state.isNoteContentTextFocused,
                singleLine = false,
                textStyle = MaterialTheme.typography.h5.copy(
                    fontSize = 20.sp
                ),
                onValueChange = viewModel::onNoteContentChange,
                onFocusChanged = {
                    viewModel.onNoteContentFocusChange(it.isFocused)
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun PreviewNotesDetailScreen() {
    MyApplicationTheme {
        NotesDetailScreen(noteId = 1, navController = NavHostController(LocalContext.current))
    }
}








