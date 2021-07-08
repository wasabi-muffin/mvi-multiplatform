package com.gmvalentino.overview.contract

import com.gmvalentino.contract.Action
import com.gmvalentino.entities.Task

sealed class OverviewAction : Action {
    object LoadTasks : OverviewAction()
    data class ToggleTaskComplete(val id: String) : OverviewAction()
    data class DeleteTask(val id: String) : OverviewAction()
    object CreateTask : OverviewAction()
    data class EditTask(val task: Task) : OverviewAction()
    data class SwipedTask(val id: String, val isReveal: Boolean) : OverviewAction()

    data class InsertTask(val task: Task) : OverviewAction()
}