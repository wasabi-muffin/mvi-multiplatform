package com.gmvalentino.main

import com.gmvalentino.State
import com.gmvalentino.entities.Task

data class MainState(
    val isLoading: Boolean = false,
    val tasks: List<Task> = emptyList()
) : State
