package com.gmvalentino.addtask.contract

import com.gmvalentino.contract.State

data class AddTaskState(
    val title: String = "",
    val isValidTitle: Boolean = false,
    val date: String = "",
    val isValidDate: Boolean = false,
    val isLoading: Boolean = false,
    val error: Throwable? = null
) : State {
    val isCreateEnabled: Boolean get() {
        return isValidDate && isValidTitle
    }
}