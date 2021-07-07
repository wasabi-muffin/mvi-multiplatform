package com.gmvalentino.main.components

import com.gmvalentino.components.BaseProcessor
import com.gmvalentino.main.contract.MainAction
import com.gmvalentino.main.contract.MainEvent
import com.gmvalentino.main.contract.MainResult
import com.gmvalentino.main.contract.MainState
import com.gmvalentino.usecases.CreateTaskUseCase
import com.gmvalentino.usecases.GetTasksUseCase
import com.gmvalentino.usecases.RemoveTaskUseCase
import com.gmvalentino.usecases.UpdateTaskUseCase
import com.gmvalentino.usecases.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

    private suspend inline fun handleLoad(): Flow<MainResult> = flow {
        emit(MainResult.Loading)
        emit(getTasksUseCase.execute(UseCase.None).let(MainResult::Tasks))
    }

    private suspend inline fun handleToggle(
        state: MainState,
        action: MainAction.Toggle
    ): Flow<MainResult> = flow {
        val isComplete = state.tasks.find { it.id == action.id }?.isComplete
        if (isComplete == null) {
            emit(MainResult.Error(action))
            error("No task with id ${action.id} found")
        }
        updateTaskUseCase.execute(
            UpdateTaskUseCase.Args(action.id, isComplete = isComplete)
        )
        emit(MainResult.Toggled(action.id, !isComplete))
    }

    private suspend inline fun handleRemove(
        state: MainState,
        action: MainAction.Remove
    ): Flow<MainResult> = flow {
        emit(MainResult.Loading)
        removeTaskUseCase.execute(
            RemoveTaskUseCase.Args(action.id)
        )
        val currentTasks = state.tasks.filter { it.id != action.id }.sortedBy { it.dueDate }
        emit(MainResult.Deleted(currentTasks))
    }

    private suspend inline fun handleCreate(
        state: MainState,
        action: MainAction.Create
    ): Flow<MainResult> = flow {
        emit(MainResult.Loading)
        val newTask = createTaskUseCase.execute(
            CreateTaskUseCase.Args(action.title, action.dueDate)
        )
        val newTasks = state.tasks + newTask
        newTasks.sortedBy { it.dueDate }
        emit(MainResult.Added(newTasks))
    }

    private suspend fun handleResolveError(action: MainAction.ResolveError) = flow {
        emit(MainResult.Error(action))
    }
}
