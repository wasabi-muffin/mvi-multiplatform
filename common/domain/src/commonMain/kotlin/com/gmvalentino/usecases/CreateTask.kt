package com.gmvalentino.usecases

import co.touchlab.kermit.Kermit
import com.gmvalentino.entities.Task
import com.gmvalentino.repositories.TaskRepository

class CreateTask(
    private val repository: TaskRepository
) : CreateTaskUseCase {
    override suspend fun execute(arguments: CreateTaskUseCase.Args) {
        repository.addTask(arguments.task)
    }
}

interface CreateTaskUseCase : UseCase<Unit, CreateTaskUseCase.Args> {
    data class Args(
        val task: Task
    ) : UseCase.Arguments()
}
