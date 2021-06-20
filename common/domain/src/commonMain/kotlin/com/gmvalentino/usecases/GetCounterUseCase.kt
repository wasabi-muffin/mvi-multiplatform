package com.gmvalentino.usecases

import com.gmvalentino.repositories.CounterRepository
import kotlinx.coroutines.flow.Flow

class GetCounterUseCase(
    private val repository: CounterRepository
) : GetCounterUseCaseProtocol() {
    override suspend fun task(arguments: None): Flow<Long> {
        return repository.getCounter()
    }
}

abstract class GetCounterUseCaseProtocol : UseCase<Flow<Long>, UseCase.None>()
