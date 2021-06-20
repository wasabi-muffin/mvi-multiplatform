package com.gmvalentino.second

import com.gmvalentino.Processable
import com.gmvalentino.Processor
import com.gmvalentino.usecases.GetCounterUseCaseProtocol
import com.gmvalentino.usecases.UpdateCounterUseCaseProtocol
import com.gmvalentino.usecases.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SecondProcessor(
    private val getCounterUseCase: GetCounterUseCaseProtocol,
    private val setCounterUseCase: UpdateCounterUseCaseProtocol,
) : Processor<SecondState, SecondAction, SecondResult> {

    override suspend fun process(
        state: SecondState,
        action: SecondAction
    ): Flow<Processable> = when (action) {
        is SecondAction.Add -> handleAdd(action)
        is SecondAction.LoadCounter -> handleLoadCounter()
    }

    private suspend fun handleAdd(
        action: SecondAction.Add
    ): Flow<Processable> = flow {
        runCatching {
            setCounterUseCase.execute(
                UpdateCounterUseCaseProtocol.Args(action.value)
            )
        }
    }

    private fun handleLoadCounter(): Flow<Processable> = flow {
        emit(SecondResult.Loading)
        emitAll(
            getCounterUseCase.execute(UseCase.None).map {
                SecondResult.Counter(it)
            }
        )
    }
}
