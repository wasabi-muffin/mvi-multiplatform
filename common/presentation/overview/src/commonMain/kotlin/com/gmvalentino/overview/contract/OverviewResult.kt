package com.gmvalentino.overview.contract

import com.gmvalentino.contract.Result
import com.gmvalentino.entities.Task

sealed class OverviewResult : Result {
    object Loading : OverviewResult()
    class TasksLoaded(val task: List<Task>) : OverviewResult()
    class TaskDeleted(val id: String) : OverviewResult()
    class TaskToggled(val id: String, val isComplete: Boolean): OverviewResult()
    class TaskSwiped(val id: String, val isReveal: Boolean): OverviewResult()
    class Error(val error: Throwable) : OverviewResult()
    class TaskInserted(val task: Task) : OverviewResult()
}
