package com.gmvalentino.main.contract

import com.gmvalentino.contract.Action
import com.gmvalentino.entities.Task

sealed class MainAction : Action {
    object LoadTasks : MainAction()
    object ResolveError : MainAction()
    data class Remove(val id: String) : MainAction()
    data class Toggle(val id: String) : MainAction()
    data class Create(val task: Task) : MainAction()
}
