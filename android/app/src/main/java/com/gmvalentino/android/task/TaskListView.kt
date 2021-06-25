package com.gmvalentino.android.task

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gmvalentino.android.AppTheme
import com.gmvalentino.entities.Task
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun TaskListView(
    modifier: Modifier = Modifier,
    tasks: List<Task>,
    onSelected: (Task) -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(tasks) { task ->
            TaskItemView(
                task = task,
                onClick = { onSelected(task) }
            )
        }
    }
}

@Preview
@Composable
fun TaskListPreview() {
    AppTheme {
        TaskListView(
            tasks = listOf(
                Task(
                    "1",
                    "Title 1",
                    "Description 1",
                    Clock.System.now().toLocalDateTime(TimeZone.UTC),
                    true
                ),
                Task(
                    "1",
                    "Title 1",
                    "Description 1",
                    Clock.System.now().toLocalDateTime(TimeZone.UTC),
                    true
                )
            ),
            onSelected = { }
        )
    }
}