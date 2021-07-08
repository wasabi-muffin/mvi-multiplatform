package com.gmvalentino.android.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gmvalentino.android.task.TaskListView
import com.gmvalentino.overview.contract.OverviewEvent
import com.gmvalentino.overview.contract.OverviewIntent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel

@Composable
fun OverviewScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val viewModel = getViewModel<OverviewViewModel>()
    val state = viewModel.state.collectAsState()

    // ref: https://www.droidcon.com/news-detail?content-id=/repository/collaboration/Groups/spaces/droidcon_hq/Documents/public/news/android-news/Architecture%20in%20Jetpack%20Compose%20-%20MVP,%20MVVM,%20and%20MVI
    LaunchedEffect(viewModel.events) {
        viewModel.events
            .onEach {
                when (it) {
                    OverviewEvent.NavigateToCreate -> navController.navigate("addtask")
                    is OverviewEvent.NavigateToEdit -> Unit
                }
            }
            .collect()
    }

    Box {
        TaskListView(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            tasks = state.value.tasks,
            revealedIds = state.value.revealedTaskIds,
            onSelected = { task ->
                viewModel.dispatch(OverviewIntent.TaskClicked(task.id))
            },
            onDelete = { task ->
                viewModel.dispatch(OverviewIntent.DeleteTaskClicked(task.id))
            },
            onEdit = { task ->
                viewModel.dispatch(OverviewIntent.EditTaskClicked(task = task))
            },
            onSwipe = { id, isReveal ->
                viewModel.dispatch(OverviewIntent.TaskSwiped(id = id, isReveal = isReveal))
            }
        )
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(all = 24.dp),
            onClick = { viewModel.dispatch(OverviewIntent.CreateTaskClicked) }
        ) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add task")
        }
        if (state.value.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}
