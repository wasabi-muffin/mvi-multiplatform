package com.gmvalentino.main.contract

import com.gmvalentino.contract.Result
import com.gmvalentino.entities.Task

sealed class MainResult : Result {
    object Loading : MainResult()
    data class Error(val action: MainAction) : MainResult()
    data class Tasks(val data: List<Task>) : MainResult()
    data class Toggled(val id: String, val isCompleted: Boolean) : MainResult()
    data class Deleted(val tasks: List<Task>) : MainResult()
    data class Added(val tasks: List<Task>) : MainResult()
}
