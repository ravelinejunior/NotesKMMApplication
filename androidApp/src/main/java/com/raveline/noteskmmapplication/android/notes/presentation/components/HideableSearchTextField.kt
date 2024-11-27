package com.raveline.noteskmmapplication.android.notes.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.raveline.noteskmmapplication.android.R

@Composable
fun HideableSearchTextField(
    modifier: Modifier = Modifier,
    text: String,
    isSearchActive: Boolean,
    onTextChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onCloseClick: () -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        AnimatedVisibility(
            visible = isSearchActive,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = onTextChange,
                shape = RoundedCornerShape(32.dp),
                placeholder = { Text(text = "Search...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("SearchTextField"),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearchClick() }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                trailingIcon = {
                    IconButton(onClick = onCloseClick) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            tint = MaterialTheme.colors.onSurface.copy(
                                alpha = ContentAlpha.medium
                            ),
                            contentDescription = stringResource(R.string.cd_close_search)
                        )
                    }
                }
            )
        }

        AnimatedVisibility(
            visible = isSearchActive,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            IconButton(
                onClick = { onCloseClick() },
                modifier = Modifier.align(Alignment.CenterEnd).testTag("CloseButton")
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = stringResource(R.string.cd_open_search)
                )
            }
        }

        AnimatedVisibility(
            visible = !isSearchActive,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            IconButton(
                onClick = { onSearchClick() },
                modifier = Modifier.testTag("SearchButton")
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.cd_open_search)
                )
            }
        }
    }
}