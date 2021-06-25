package com.gmvalentino.android.task

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.gmvalentino.main.MainIntent
import org.koin.androidx.compose.getViewModel

@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel = getViewModel<TaskViewModel>()
    val state = viewModel.store.state.collectAsState()

    TaskListView(
        tasks = state.value.tasks,
        onSelected = { task ->
            viewModel.store.dispatch(MainIntent.Toggle(task.id))
        }
    )
}
