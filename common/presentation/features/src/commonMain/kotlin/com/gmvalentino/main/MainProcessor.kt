package com.gmvalentino.main

import com.gmvalentino.BaseProcessor
import com.gmvalentino.usecases.GetTasksUseCaseProtocol
import com.gmvalentino.usecases.UpdateTaskUseCaseProtocol
import com.gmvalentino.usecases.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MainProcessor(
    private val getTasksUseCase: GetTasksUseCaseProtocol,
    private val updateTaskUseCase: UpdateTaskUseCaseProtocol
) : BaseProcessor<MainState, MainAction, MainResult, MainEvent>() {

    override suspend fun process(
        state: MainState,
        action: MainAction
    ): Flow<MainResult> = when (action) {
        MainAction.LoadTasks -> handleLoad()
        is MainAction.Toggle -> handleToggle(state, action)
    }

    private suspend fun handleLoad(): Flow<MainResult> = flow {
        emit(MainResult.Loading)
        emitAll(getTasksUseCase.execute(UseCase.None).map(MainResult::Tasks))
    }

    private suspend fun handleToggle(
        state: MainState,
        action: MainAction.Toggle
    ): Flow<MainResult> = flow {
        // runCatching {
        val isComplete = state.tasks.find { it.id == action.id }?.isComplete ?: false
        // updateTaskUseCase.execute(
        //     UpdateTaskUseCaseProtocol.Args(
        //         action.id,
        //         !isComplete
        //     )
        // )
        publish(MainEvent.Test)
        emit(MainResult.Toggled(action.id, !isComplete))
        // }
    }
}
