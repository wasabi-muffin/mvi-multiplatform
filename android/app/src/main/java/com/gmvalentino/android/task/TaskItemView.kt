package com.gmvalentino.android.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gmvalentino.android.AppTheme
import com.gmvalentino.entities.Task
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayAt

@Composable
fun TaskItemView(
    modifier: Modifier = Modifier,
    task: Task,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TaskCheckBox(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically),
            color = Color.Blue,
            isChecked = task.isComplete
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = task.title,
                style = MaterialTheme.typography.body1
            )
            Spacer(
                modifier = Modifier.height(2.dp)
            )
            Text(
                text = task.dueDate.toString(),
                style = MaterialTheme.typography.caption.copy(
                    color = Color.LightGray
                ),
            )
        }
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    AppTheme {
        Column() {
            TaskItemView(
                task = Task(
                    "1",
                    "Title 1",
                    Clock.System.todayAt(TimeZone.UTC),
                    true
                ),
                onClick = { }
            )
            TaskItemView(
                task = Task(
                    "1",
                    "Testing the out with a very very very very very very very very very very very very very very very very very very very very very long title",
                    Clock.System.todayAt(TimeZone.UTC),
                    false
                ),
                onClick = { }
            )
        }
    }
}