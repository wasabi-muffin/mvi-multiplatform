package com.gmvalentino.usecases

import com.gmvalentino.entities.Task
import com.gmvalentino.repositories.TaskRepository

class CreateTaskUseCase(
    private val repository: TaskRepository
) : CreateTaskUseCaseProtocol() {
    override suspend fun task(arguments: Args) {
        return repository.addTask(arguments.task)
    }
}

abstract class CreateTaskUseCaseProtocol : UseCase<Unit, CreateTaskUseCaseProtocol.Args>() {
    data class Args(
        val task: Task
    ) : UseCase.Arguments()
}
