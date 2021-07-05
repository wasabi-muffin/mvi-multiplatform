package com.gmvalentino.main

import com.gmvalentino.State
import com.gmvalentino.entities.Task

data class MainState(
    val isLoading: Boolean = false,
    val isError: Error = Error.NONE,
    val tasks: List<Task> = emptyList(),
) : State {
    enum class Error {
        NONE, LOAD, CREATE, REMOVE, UPDATE
    }
}
