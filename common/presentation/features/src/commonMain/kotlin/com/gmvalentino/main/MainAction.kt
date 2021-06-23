package com.gmvalentino.main

import com.gmvalentino.Action

sealed class MainAction : Action {
    data class Toggle(val id: String) : MainAction()
    object LoadTasks : MainAction()
}
