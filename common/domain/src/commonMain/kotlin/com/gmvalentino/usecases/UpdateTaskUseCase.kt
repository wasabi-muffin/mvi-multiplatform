package com.gmvalentino.usecases

import com.gmvalentino.repositories.TaskRepository

class UpdateTaskUseCase(
    private val repository: TaskRepository
) : UpdateTaskUseCaseProtocol {
    override suspend fun execute(arguments: UpdateTaskUseCaseProtocol.Args) {
        return repository.updateTask(arguments.id, arguments.isComplete)
    }
}

interface UpdateTaskUseCaseProtocol : UseCase<Unit, UpdateTaskUseCaseProtocol.Args> {
    data class Args(
        val id: String,
        val isComplete: Boolean
    ) : UseCase.Arguments()
}
