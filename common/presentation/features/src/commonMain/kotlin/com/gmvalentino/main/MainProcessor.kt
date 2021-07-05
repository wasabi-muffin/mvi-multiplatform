package com.gmvalentino.main

import com.gmvalentino.BaseProcessor
import com.gmvalentino.usecases.CreateTaskUseCase
import com.gmvalentino.usecases.GetTasksUseCase
import com.gmvalentino.usecases.RemoveTaskUseCase
import com.gmvalentino.usecases.UpdateTaskUseCase
import com.gmvalentino.usecases.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MainProcessor(
    private val getTasksUseCase: GetTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val removeTaskUseCase: RemoveTaskUseCase,
    private val createTaskUseCase: CreateTaskUseCase
) : BaseProcessor<MainState, MainAction, MainResult, MainEvent>() {

    override suspend fun process(
        state: MainState,
        action: MainAction
    ): Flow<MainResult> = when (action) {
        is MainAction.LoadTasks -> handleLoad()
        is MainAction.Toggle -> handleToggle(state, action)
        is MainAction.Remove -> handleRemove(state, action)
        is MainAction.Create -> handleCreate(state, action)
        is MainAction.ResolveError -> handleResolveError(action)
    }

    private suspend fun handleLoad(): Flow<MainResult> = flow {
        emit(MainResult.Loading)
        emitAll(getTasksUseCase.execute(UseCase.None).map(MainResult::Tasks))
    }

    private suspend fun handleToggle(
        state: MainState,
        action: MainAction.Toggle
    ): Flow<MainResult> = flow {
        val isComplete = state.tasks.find { it.id == action.id }?.isComplete
        if (isComplete == null) {
            emit(MainResult.Error(action))
            error("No task with id ${action.id} found")
        }
        runCatching {
            updateTaskUseCase.execute(
                UpdateTaskUseCase.Args(action.id, !isComplete)
            )
        }.onSuccess {
            emit(MainResult.Toggled(action.id, !isComplete))
        }.onFailure {
            emit(MainResult.Error(action))
        }
    }

    private suspend fun handleRemove(
        state: MainState,
        action: MainAction.Remove
    ): Flow<MainResult> = flow {
        emit(MainResult.Loading)
        runCatching {
            removeTaskUseCase.execute(
                RemoveTaskUseCase.Args(action.id)
            )
        }.onSuccess {
            val currentTasks = state.tasks.filter { it.id != action.id }
            currentTasks.sortedBy { it.date }
            emit(MainResult.Deleted(currentTasks))
        }.onFailure {
            emit(MainResult.Error(action))
        }
    }

    private suspend fun handleCreate(
        state: MainState,
        action: MainAction.Create
    ): Flow<MainResult> = flow {
        emit(MainResult.Loading)
        runCatching {
            createTaskUseCase.execute(
                CreateTaskUseCase.Args(action.task)
            )
        }.onSuccess {
            val currentTasks = state.tasks + action.task
            currentTasks.sortedBy { it.date }
            emit(MainResult.Added(currentTasks))
        }.onFailure {
            emit(MainResult.Error(action))
        }
    }

    private suspend fun handleResolveError(action: MainAction.ResolveError) = flow {
        emit(MainResult.Error(action))
    }
}
