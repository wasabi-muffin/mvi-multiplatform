package com.gmvalentino.overview.contract

import com.gmvalentino.contract.State
import com.gmvalentino.entities.Task

data class OverviewState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val revealedTaskIds: Set<String> = setOf()
) : State