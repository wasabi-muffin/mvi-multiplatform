package com.gmvalentino.usecases

import com.gmvalentino.entities.Task
import com.gmvalentino.repositories.TaskRepository

class CreateTaskUseCase(
    private val repository: TaskRepository
) : CreateTaskUseCaseProtocol {
    override suspend fun execute(arguments: CreateTaskUseCaseProtocol.Args) {
        return repository.addTask(arguments.task)
    }
}

interface CreateTaskUseCaseProtocol : UseCase<Unit, CreateTaskUseCaseProtocol.Args> {
    data class Args(
        val task: Task
    ) : UseCase.Arguments()
}
