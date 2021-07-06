package com.gmvalentino.usecases

import com.gmvalentino.repositories.TaskRepository

class RemoveTask(
    private val repository: TaskRepository
) : RemoveTaskUseCase {
    override suspend fun execute(arguments: RemoveTaskUseCase.Args) {
        repository.deleteTask(arguments.id)
    }
}

interface RemoveTaskUseCase : UseCase<Unit, RemoveTaskUseCase.Args> {
    data class Args(
        val id: String
    ) : UseCase.Arguments()
}
