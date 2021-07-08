package com.gmvalentino.overview.contract

import com.gmvalentino.contract.Intent
import com.gmvalentino.entities.Task

sealed class OverviewIntent : Intent {
    data class TaskClicked(val id: String) : OverviewIntent()
    data class TaskSwiped(val id: String, val isReveal: Boolean) : OverviewIntent()
    data class DeleteTaskClicked(val id: String) : OverviewIntent()
    data class EditTaskClicked(val task: Task) : OverviewIntent()
    object CreateTaskClicked : OverviewIntent()
    object PullRefresh : OverviewIntent()
}