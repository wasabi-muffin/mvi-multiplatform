package com.gmvalentino.main.contract

import com.gmvalentino.contract.Action
import com.gmvalentino.entities.Task
import kotlinx.datetime.LocalDate

sealed class MainAction : Action {
    object LoadTasks : MainAction()
    object ResolveError : MainAction()
    data class Remove(val id: String) : MainAction()
    data class Toggle(val id: String) : MainAction()
    data class Create(val title: String, val dueDate: LocalDate) : MainAction()
}
