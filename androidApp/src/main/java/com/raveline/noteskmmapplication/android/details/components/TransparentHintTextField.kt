package com.raveline.noteskmmapplication.android.details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle

@Composable
fun TransparentHintTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    onFocusChanged: (FocusState) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit,
    isHintVisible: Boolean
) {
    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            decorationBox = { innerTextField ->
                if (isHintVisible) {
                    if (text.isEmpty()) {
                        Text(
                            text = hint,
                            style = textStyle,
                            color = Color.DarkGray,
                        )
                    }
                }
                innerTextField()
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChanged(it) },
            cursorBrush = SolidColor(Color.Transparent)
        )
        if (isHintVisible) {
            Text(
                text = hint,
                style = textStyle,
                color = Color.DarkGray,
            )
        }
    }
}