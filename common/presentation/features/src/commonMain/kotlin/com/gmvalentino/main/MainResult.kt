package com.gmvalentino.main

import com.gmvalentino.Result
import com.gmvalentino.entities.Task

sealed class MainResult : Result {
    object Loading : MainResult()
    data class Tasks(val data: List<Task>) : MainResult()
    data class Toggled(val id: String, val isCompleted: Boolean) : MainResult()
}
