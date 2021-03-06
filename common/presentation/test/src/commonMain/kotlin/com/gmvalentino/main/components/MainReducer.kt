package com.gmvalentino.main.components

import com.gmvalentino.components.Reducer
import com.gmvalentino.main.contract.MainAction
import com.gmvalentino.main.contract.MainResult
import com.gmvalentino.main.contract.MainState

class MainReducer : Reducer<MainResult, MainState> {
    override suspend fun reduce(result: MainResult, state: MainState): MainState {
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
