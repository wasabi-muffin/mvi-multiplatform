package com.gmvalentino.android.task

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.gmvalentino.entities.Task
import com.gmvalentino.main.MainIntent
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.androidx.compose.getViewModel

@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel = getViewModel<TaskViewModel>()
    val state = viewModel.state.collectAsState()

    Column {
        Button(onClick = { viewModel.dispatch(MainIntent.DeleteClicked("1")) }) {
            Text("Delete")
        }
        Button(onClick = {
            viewModel.dispatch(
                MainIntent.CreateClicked(
                    Task(
                        id = "1",
                        title = "Created Task",
                        details = "Details",
                        date = Clock.System.now().toLocalDateTime(
                            TimeZone.currentSystemDefault()
                        ),
                        isComplete = false
                    )
                )
            )
        }) {
            Text("Create")
        }
        TaskListView(
            tasks = state.value.tasks,
            onSelected = { task ->
                viewModel.dispatch(MainIntent.TaskClicked(task.id))
            }
        )
    }
}
