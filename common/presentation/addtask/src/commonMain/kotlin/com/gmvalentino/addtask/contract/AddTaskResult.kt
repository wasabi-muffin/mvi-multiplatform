package com.gmvalentino.addtask.contract

import com.gmvalentino.contract.Result
import com.gmvalentino.entities.Task

sealed class AddTaskResult : Result {
    object Loading : AddTaskResult()
    class Error(val error: Throwable) : AddTaskResult()
    class TitleChanged(val title: String, val isValid: Boolean) : AddTaskResult()
    class DateChanged(val date: String, val isValid: Boolean) : AddTaskResult()
    class TaskCreated(val task: Task) : AddTaskResult()
}
