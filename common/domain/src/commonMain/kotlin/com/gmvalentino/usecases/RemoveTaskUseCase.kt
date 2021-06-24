package com.gmvalentino.usecases

import com.gmvalentino.repositories.TaskRepository

class RemoveTaskUseCase(
    private val repository: TaskRepository
) : RemoveTaskUseCaseProtocol() {
    override suspend fun task(arguments: Args) {
        return repository.deleteTask(arguments.id)
    }
}

abstract class RemoveTaskUseCaseProtocol : UseCase<Unit, RemoveTaskUseCaseProtocol.Args>() {
    data class Args(
        val id: String
    ) : UseCase.Arguments()
}