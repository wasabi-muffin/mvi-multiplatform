package com.gmvalentino.usecases

import com.gmvalentino.repositories.CounterRepository

class UpdateCounterUseCase(
    private val repository: CounterRepository
) : UpdateCounterUseCaseProtocol() {

    override suspend fun task(arguments: Args) {
        repository.setCounter(arguments.value.toLong())
    }
}

abstract class UpdateCounterUseCaseProtocol : UseCase<Unit, UpdateCounterUseCaseProtocol.Args>() {
    data class Args(
        val value: Int
    ) : UseCase.Arguments()
}
