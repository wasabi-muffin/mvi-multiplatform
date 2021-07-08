package com.gmvalentino.addtask.components

import com.gmvalentino.addtask.contract.AddTaskAction
import com.gmvalentino.addtask.contract.AddTaskEvent
import com.gmvalentino.addtask.contract.AddTaskResult
import com.gmvalentino.addtask.contract.AddTaskState
import com.gmvalentino.components.BaseProcessor
import com.gmvalentino.usecases.CreateTaskUseCase
import com.gmvalentino.usecases.FormatAndValidateTaskDateUseCase
import com.gmvalentino.usecases.ValidateTaskTitleUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddTaskProcessor(
    private val validateTaskTitleUseCase: ValidateTaskTitleUseCase,
    private val formatAndValidateTaskDateUseCase: FormatAndValidateTaskDateUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
) :
    BaseProcessor<AddTaskState, AddTaskAction, AddTaskResult, AddTaskEvent>() {
    override suspend fun process(
        state: AddTaskState,
        action: AddTaskAction
    ): Flow<AddTaskResult> = when (action) {
        is AddTaskAction.ValidateTitle -> handleValidateTitle(action)
        is AddTaskAction.FormatAndValidateDate -> handleFormatAndValidateDate(action)
        is AddTaskAction.CreateTask -> handleCreateTask(state)
    }

    private fun handleValidateTitle(
        action: AddTaskAction.ValidateTitle
    ): Flow<AddTaskResult> = flow {
        val isValid = validateTaskTitleUseCase.execute(
            ValidateTaskTitleUseCase.Args(action.title)
        )
        emit(AddTaskResult.TitleChanged(action.title, isValid))
    }

    private fun handleFormatAndValidateDate(
        action: AddTaskAction.FormatAndValidateDate
    ): Flow<AddTaskResult> = flow {
        val (formattedDate, isValid) = formatAndValidateTaskDateUseCase.execute(
            FormatAndValidateTaskDateUseCase.Args(action.date)
        )
        emit(AddTaskResult.DateChanged(formattedDate, isValid))
    }

    private fun handleCreateTask(
        state: AddTaskState
    ): Flow<AddTaskResult> = flow {
        emit(AddTaskResult.Loading)
        runCatching {
            createTaskUseCase.execute(
                CreateTaskUseCase.Args(
                    state.title,
                    state.date
                )
            )
        }.fold(
            onSuccess = {
                publish(AddTaskEvent.Close)
            },
            onFailure = {
                emit(AddTaskResult.Error(it))
            }
        )
    }
}