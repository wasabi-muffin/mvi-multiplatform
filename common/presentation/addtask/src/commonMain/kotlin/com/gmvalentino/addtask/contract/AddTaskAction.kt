package com.gmvalentino.addtask.contract

import com.gmvalentino.contract.Action
import com.gmvalentino.entities.Task

sealed class AddTaskAction : Action {
    data class ValidateTitle(val title: String) : AddTaskAction()
    data class FormatAndValidateDate(val date: String) : AddTaskAction()
    object CreateTask : AddTaskAction()
}