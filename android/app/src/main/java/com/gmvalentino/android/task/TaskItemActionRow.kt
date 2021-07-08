package com.gmvalentino.android.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ActionsRow(
    width: Dp,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    val horizontalPadding = 24.dp
    val buttonWidth = (width - horizontalPadding / 2) / 2

    Row(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        IconButton(
            modifier = Modifier
                .width(buttonWidth)
                .fillMaxHeight()
                .background(
                    color = Color.Red,
                    shape = RoundedCornerShape(
                        topStart = 10.dp,
                        bottomStart = 10.dp
                    )
                )
                .align(CenterVertically),
            onClick = {
                onDelete()
            },
            content = {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    tint = Color.Black,
                    contentDescription = "Delete action",
                )
            }
        )
        IconButton(
            modifier = Modifier
                .width(buttonWidth)
                .fillMaxHeight()
                .background(
                    color = Color.Yellow,
                    shape = RoundedCornerShape(
                        topEnd = 10.dp,
                        bottomEnd = 10.dp
                    )
                )
                .align(CenterVertically),
            onClick = onEdit,
            content = {
                Icon(
                    imageVector = Icons.Rounded.Edit,
                    tint = Color.Black,
                    contentDescription = "Edit action",
                )
            },
        )
    }
}