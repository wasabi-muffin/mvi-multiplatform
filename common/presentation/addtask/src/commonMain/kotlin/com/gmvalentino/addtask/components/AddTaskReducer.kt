package com.gmvalentino.addtask.components

import com.gmvalentino.addtask.contract.AddTaskResult
import com.gmvalentino.addtask.contract.AddTaskState
import com.gmvalentino.components.Reducer

class AddTaskReducer : Reducer<AddTaskResult, AddTaskState> {
    override suspend fun reduce(result: AddTaskResult, state: AddTaskState): AddTaskState =
        when (result) {
            is AddTaskResult.Loading -> state.reduceLoading()
            is AddTaskResult.Error -> state.reduceError(result)
            is AddTaskResult.TitleChanged -> state.reduceTitleChanged(result)
            is AddTaskResult.DateChanged -> state.reduceDateChanged(result)
            is AddTaskResult.TaskCreated -> state
        }

    private fun AddTaskState.reduceLoading(): AddTaskState {
        return copy(isLoading = true, error = null)
    }

    private fun AddTaskState.reduceError(result: AddTaskResult.Error): AddTaskState {
        return copy(isLoading = false, error = result.error)
    }

    private fun AddTaskState.reduceTitleChanged(result: AddTaskResult.TitleChanged): AddTaskState {
        return copy(
            title = result.title,
            isValidTitle = result.isValid
        )
    }

    private fun AddTaskState.reduceDateChanged(result: AddTaskResult.DateChanged): AddTaskState {
        return copy(
            date = result.date,
            isValidDate = result.isValid
        )
    }

}