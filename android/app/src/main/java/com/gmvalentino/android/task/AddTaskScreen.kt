package com.gmvalentino.android.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AddTaskScreen() {
    Box(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxHeight()
            .fillMaxWidth()
    )
}