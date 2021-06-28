package com.gmvalentino.android.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmvalentino.android.AppTheme
import com.gmvalentino.entities.Task
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun TaskItemView(
    modifier: Modifier = Modifier,
    task: Task,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = task.title, style = TextStyle(fontSize = 20.sp))
            Text(
                text = task.details,
                style = TextStyle(color = Color.DarkGray, fontSize = 14.sp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Checkbox(
            checked = task.isComplete,
            onCheckedChange = null,
            modifier = Modifier.padding(
                20.dp
            )
        )
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    AppTheme {
        TaskItemView(
            task = Task(
                "1",
                "Title 1",
                "Description 1",
                Clock.System.now().toLocalDateTime(TimeZone.UTC),
                true
            ),
            onClick = { }
        )
    }
}
