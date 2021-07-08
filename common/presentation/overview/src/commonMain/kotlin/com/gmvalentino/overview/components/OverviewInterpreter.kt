package com.gmvalentino.overview.components

import com.gmvalentino.components.Interpreter
import com.gmvalentino.overview.contract.OverviewAction
import com.gmvalentino.overview.contract.OverviewIntent

class OverviewInterpreter : Interpreter<OverviewIntent, OverviewAction> {
    override suspend fun interpret(intent: OverviewIntent): OverviewAction = when (intent) {
        is OverviewIntent.TaskClicked -> OverviewAction.ToggleTaskComplete(intent.id)
        is OverviewIntent.TaskSwiped -> OverviewAction.SwipedTask(intent.id, intent.isReveal)
        is OverviewIntent.DeleteTaskClicked -> OverviewAction.DeleteTask(intent.id)
        is OverviewIntent.EditTaskClicked -> OverviewAction.EditTask(intent.task)
        OverviewIntent.CreateTaskClicked -> OverviewAction.CreateTask
        OverviewIntent.PullRefresh -> OverviewAction.LoadTasks
    }
}