package com.gmvalentino.main

import com.gmvalentino.Processable
import com.gmvalentino.Processor
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
) : Processor<MainState, MainAction, MainResult> {

    override suspend fun process(
        state: MainState,
        action: MainAction
    ): Flow<Processable> = when (action) {
        MainAction.LoadTasks -> handleLoad()
        is MainAction.Toggle -> handleAdd(state, action)
    }

    private suspend fun handleLoad(): Flow<Processable> = flow {
        emit(MainResult.Loading)
        emitAll(
            getTasksUseCase.execute(UseCase.None).map {
                MainResult.Tasks(it)
            }
        )
    }

    private fun handleAdd(
        state: MainState,
        action: MainAction.Toggle
    ): Flow<Processable> = flow {
        runCatching {
            val isComplete = state.tasks.find { it.id == action.id }?.isComplete ?: false
            updateTaskUseCase.execute(
                UpdateTaskUseCaseProtocol.Args(
                    action.id,
                    !isComplete
                )
            )
        }
    }
}
