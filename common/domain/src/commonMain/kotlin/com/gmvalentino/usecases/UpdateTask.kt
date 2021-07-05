package com.gmvalentino.usecases

import com.gmvalentino.repositories.TaskRepository

class UpdateTask(
    private val repository: TaskRepository
) : UpdateTaskUseCase {
    override suspend fun execute(arguments: UpdateTaskUseCase.Args) {
        return repository.updateTask(arguments.id, arguments.isComplete)
    }
}

interface UpdateTaskUseCase : UseCase<Unit, UpdateTaskUseCase.Args> {
    data class Args(
        val id: String,
        val isComplete: Boolean
    ) : UseCase.Arguments()
}
