package com.gmvalentino.android.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TaskCheckBox(
    modifier: Modifier = Modifier,
    color: Color,
    isChecked: Boolean
) {
    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .background(
                color = if (isChecked) color else Color.Transparent,
            )
            .border(
                width = if (isChecked) 0.dp else 2.dp,
                color = color,
                shape = CircleShape
            )

    ) {
        if (isChecked)
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = "Check",
                tint = Color.White
            )
    }
}

@Preview
@Composable
fun TaskCheckBox_Preview() {
    Column {
        TaskCheckBox(modifier = Modifier.size(24.dp), color = Color.Blue, isChecked = true)
        TaskCheckBox(modifier = Modifier.size(24.dp), color = Color.Red, isChecked = false)
    }
}