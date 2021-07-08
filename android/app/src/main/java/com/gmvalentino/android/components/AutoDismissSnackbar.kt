package com.gmvalentino.android.components

import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun AutoDismissSnackbar(
    modifier: Modifier = Modifier,
    text: String,
    autoDismiss: Boolean = true,
    timeout: Long = 2000,
    onDismiss: () -> Unit = {}
) {
    var show by remember { mutableStateOf(true) }

    if (show) {
        Snackbar(
            modifier = modifier,
        ) {
            Text(text = text)
        }
    }

    if (autoDismiss && show) {
        LaunchedEffect(text) {
            delay(timeout)
            show = false
            onDismiss()
        }
    }
}