package com.gmvalentino.android.task

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.gmvalentino.main.contract.MainIntent
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayAt
import org.koin.androidx.compose.getViewModel

@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel = getViewModel<TaskViewModel>()
    val state = viewModel.state.collectAsState()

    Column {
        Button(onClick = {
            viewModel.dispatch(
                MainIntent.DeleteClicked(
                    state.value.tasks.firstOrNull()?.id ?: ""
                )
            )
        }) {
            Text("Delete")
        }
        Button(onClick = {
            viewModel.dispatch(
                MainIntent.CreateClicked(
                    title = "Created Task",
                    dueDate = Clock.System.todayAt(
                        TimeZone.currentSystemDefault()
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
