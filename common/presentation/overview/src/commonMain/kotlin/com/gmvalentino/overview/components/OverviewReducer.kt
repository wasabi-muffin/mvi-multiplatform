package com.gmvalentino.overview.components

import com.gmvalentino.components.Reducer
import com.gmvalentino.overview.contract.OverviewResult
import com.gmvalentino.overview.contract.OverviewState

class OverviewReducer : Reducer<OverviewResult, OverviewState> {
    override suspend fun reduce(result: OverviewResult, state: OverviewState): OverviewState =
        when (result) {
            is OverviewResult.Loading -> handleLoading(state)
            is OverviewResult.TasksLoaded -> handleTasksLoaded(result, state)
            is OverviewResult.TaskDeleted -> handleTaskDeleted(result, state)
            is OverviewResult.Error -> handleError(result, state)
            is OverviewResult.TaskToggled -> handleTaskToggled(result, state)
            is OverviewResult.TaskSwiped -> handleTaskSwiped(result, state)
            is OverviewResult.TaskInserted -> handleTaskInserted(result, state)
        }

    private fun handleLoading(state: OverviewState): OverviewState {
        return state.copy(
            isLoading = true,
            error = null,
        )
    }

    private fun handleTasksLoaded(
        result: OverviewResult.TasksLoaded,
        state: OverviewState
    ): OverviewState {
        return state.copy(
            isLoading = false,
            tasks = result.task.sortedBy { it.dueDate },
            revealedTaskIds = setOf()
        )
    }

    private fun handleTaskDeleted(
        result: OverviewResult.TaskDeleted,
        state: OverviewState
    ): OverviewState {
        return state.copy(
            isLoading = false,
            tasks = state.tasks.filter { it.id != result.id },
        )
    }

    private fun handleError(result: OverviewResult.Error, state: OverviewState): OverviewState {
        return state.copy(
            isLoading = false,
            error = result.error,
        )
    }

    private fun handleTaskToggled(
        result: OverviewResult.TaskToggled,
        state: OverviewState
    ): OverviewState {
        return state.copy(
            isLoading = false,
            tasks = state.tasks.transformWhen({ it.id == result.id }) {
                copy(isComplete = result.isComplete)
            }
        )
    }

    private fun handleTaskSwiped(
        result: OverviewResult.TaskSwiped,
        state: OverviewState
    ): OverviewState {
        return state.copy(
            revealedTaskIds = state.revealedTaskIds.toMutableSet().apply {
                if (result.isReveal) {
                    add(result.id)
                } else {
                    remove(result.id)
                }
            }
        )
    }

    private fun handleTaskInserted(
        result: OverviewResult.TaskInserted,
        state: OverviewState
    ) : OverviewState {
        return state.copy(
            tasks = state.tasks.toMutableList().apply {
                add(result.task)
            }.sortedBy { it.dueDate }
        )
    }
}

// TODO: Move to utils
fun <T> List<T>.transformWhen(condition: (T) -> Boolean, value: T.() -> T): List<T> {
    return map {
        if (condition(it)) {
            it.value()
        } else {
            it
        }
    }
}