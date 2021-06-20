package com.gmvalentino.main

import com.gmvalentino.Processable
import com.gmvalentino.Processor
import com.gmvalentino.usecases.GetCounterUseCaseProtocol
import com.gmvalentino.usecases.UpdateCounterUseCaseProtocol
import com.gmvalentino.usecases.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MainProcessor(
    private val getCounterUseCase: GetCounterUseCaseProtocol,
    private val setCounterUseCase: UpdateCounterUseCaseProtocol,
) : Processor<MainState, MainAction, MainResult> {

    override suspend fun process(
        state: MainState,
        action: MainAction
    ): Flow<Processable> = when (action) {
        is MainAction.Add -> handleAdd(action)
        is MainAction.LoadCounter -> handleLoadCounter()
    }

    private suspend fun handleAdd(
        action: MainAction.Add
    ): Flow<Processable> = flow {
        runCatching {
            setCounterUseCase.execute(
                UpdateCounterUseCaseProtocol.Args(action.value)
            )
        }
    }

    private fun handleLoadCounter(): Flow<Processable> = flow {
        emit(MainResult.Loading)
        emitAll(
            getCounterUseCase.execute(UseCase.None).map {
                MainResult.Counter(it)
            }
        )
    }
}
