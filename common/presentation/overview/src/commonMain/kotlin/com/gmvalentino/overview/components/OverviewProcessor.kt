package com.gmvalentino.overview.components

import com.gmvalentino.components.BaseProcessor
import com.gmvalentino.overview.contract.OverviewAction
import com.gmvalentino.overview.contract.OverviewEvent
import com.gmvalentino.overview.contract.OverviewResult
import com.gmvalentino.overview.contract.OverviewState
import com.gmvalentino.usecases.GetTasksUseCase
import com.gmvalentino.usecases.RemoveTaskUseCase
import com.gmvalentino.usecases.UpdateTaskUseCase
import com.gmvalentino.usecases.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OverviewProcessor(
    private val getTasksUseCase: GetTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val removeTaskUseCase: RemoveTaskUseCase
) :
    BaseProcessor<OverviewState, OverviewAction, OverviewResult, OverviewEvent>() {
    override suspend fun process(
        state: OverviewState,
        action: OverviewAction
    ): Flow<OverviewResult> = when (action) {
        is OverviewAction.LoadTasks -> handleLoadTasks()
        is OverviewAction.ToggleTaskComplete -> handleToggleTaskComplete(action, state)
        is OverviewAction.DeleteTask -> handleDeleteTask(action)
        is OverviewAction.CreateTask -> handleCreateTask()
        is OverviewAction.EditTask -> handleEditTask(action)
        is OverviewAction.SwipedTask -> handleOpenSwipeMenuTask(action, state)
    }

    private fun handleLoadTasks(): Flow<OverviewResult> = flow {
        emit(OverviewResult.Loading)
        val result = getTasksUseCase.execute(UseCase.None)
        emit(OverviewResult.TasksLoaded(result))
    }

    private fun handleToggleTaskComplete(
        action: OverviewAction.ToggleTaskComplete,
        state: OverviewState
    ): Flow<OverviewResult> = flow {
        emit(OverviewResult.Loading)
        val isComplete = state.tasks.find { it.id == action.id }?.isComplete
        if (isComplete == null) {
            emit(OverviewResult.Error(Exception("Task with id ${action.id} not found")))
            return@flow
        }

        runCatching {
            updateTaskUseCase.execute(
                UpdateTaskUseCase.Args(id = action.id, isComplete = !isComplete)
            )
        }.fold(
            onSuccess = {
                emit(
                    OverviewResult.TaskToggled(
                        id = action.id,
                        isComplete = !isComplete
                    )
                )
            },
            onFailure = {
                emit(OverviewResult.Error(it))
            }
        )
    }

    private fun handleDeleteTask(action: OverviewAction.DeleteTask): Flow<OverviewResult> = flow {
        emit(OverviewResult.Loading)
        runCatching {
            removeTaskUseCase.execute(
                RemoveTaskUseCase.Args(action.id)
            )
        }.fold(
            onSuccess = {
                emit(OverviewResult.TaskDeleted(action.id))
            },
            onFailure = {
                emit(OverviewResult.Error(it))
            }
        )
    }

    private fun handleCreateTask(): Flow<OverviewResult> = flow {
        publish(OverviewEvent.NavigateToCreate)
    }

    private fun handleEditTask(action: OverviewAction.EditTask): Flow<OverviewResult> = flow {
        publish(OverviewEvent.NavigateToEdit(action.task))
    }

    private fun handleOpenSwipeMenuTask(
        action: OverviewAction.SwipedTask,
        state: OverviewState
    ): Flow<OverviewResult> = flow {
        emit(OverviewResult.TaskSwiped(action.id, action.isReveal))
    }
}