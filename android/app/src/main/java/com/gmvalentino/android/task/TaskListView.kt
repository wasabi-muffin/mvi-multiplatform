package com.gmvalentino.android.task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gmvalentino.android.AppTheme
import com.gmvalentino.entities.Task
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayAt

@Composable
fun TaskListView(
    modifier: Modifier = Modifier,
    tasks: List<Task>,
    revealedIds: Set<String>,
    onSelected: (Task) -> Unit,
    onDelete: (Task) -> Unit,
    onEdit: (Task) -> Unit,
    onSwipe: (String, Boolean) -> Unit
) {
    val offset = LocalContext.current.resources.displayMetrics.widthPixels / 3f

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 40.dp)
    ) {
        items(tasks, { it.id }) { task ->
            Box(Modifier.fillMaxWidth()) {
                ActionsRow(
                    modifier = Modifier
                        .align(Alignment.CenterStart),
                    width = with(LocalDensity.current) { offset.toDp() },
                    onDelete = { onDelete(task) },
                    onEdit = { onEdit(task) }
                )
                DraggableCard(
                    cardOffset = offset,
                    isRevealed = revealedIds.contains(task.id),
                    onReveal = { onSwipe(task.id, true) },
                    onCollapse = { onSwipe(task.id, false) },
                ) {
                    TaskItemView(
                        task = task,
                        onClick = { if (!revealedIds.contains(task.id)) onSelected(task) }
                    )
                }
            }
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
                    Clock.System.todayAt(TimeZone.UTC),
                    true
                ),
                Task(
                    "2",
                    "Title 1",
                    Clock.System.todayAt(TimeZone.UTC),
                    true
                )
            ),
            revealedIds = setOf("2"),
            onSelected = { },
            onDelete = { },
            onEdit = { },
            onSwipe = { _, _ -> }
        )
    }
}