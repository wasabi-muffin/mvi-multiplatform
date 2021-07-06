package com.gmvalentino.main

import com.gmvalentino.Reducer

class MainReducer : Reducer<MainState, MainResult> {
    override suspend fun reduce(state: MainState, result: MainResult): MainState {
        return when (result) {
            is MainResult.Tasks -> {
                state.copy(
                    tasks = result.data,
                    isLoading = false
                )
            }
            is MainResult.Loading -> {
                state.copy(isLoading = true)
            }
            is MainResult.Toggled -> {
                state.copy(
                    tasks = state.tasks.map {
                        if (it.id == result.id) {
                            it.copy(isComplete = result.isCompleted)
                        } else {
                            it
                        }
                    }
                )
            }
            is MainResult.Deleted -> {
                state.copy(
                    isLoading = false,
                    tasks = result.tasks
                )
            }
            is MainResult.Added -> {
                state.copy(
                    isLoading = false,
                    tasks = result.tasks
                )
            }
            is MainResult.Error -> {
                val errorState = when (result.action) {
                    MainAction.LoadTasks -> MainState.Error.LOAD
                    is MainAction.Remove -> MainState.Error.REMOVE
                    is MainAction.Toggle -> MainState.Error.UPDATE
                    is MainAction.Create -> MainState.Error.CREATE
                    is MainAction.ResolveError -> MainState.Error.NONE
                }
                state.copy(
                    isLoading = false,
                    isError = errorState
                )
            }
        }
    }
}
