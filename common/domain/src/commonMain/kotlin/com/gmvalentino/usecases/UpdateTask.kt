package com.gmvalentino.usecases

import com.gmvalentino.repositories.TaskRepository
import kotlinx.datetime.LocalDate

class UpdateTask(
    private val repository: TaskRepository
) : UpdateTaskUseCase {
    override suspend fun execute(arguments: UpdateTaskUseCase.Args) {
        repository.updateTask(
            arguments.id,
            arguments.title,
            arguments.dueDate,
            arguments.isComplete
        )
    }
}

interface UpdateTaskUseCase : UseCase<Unit, UpdateTaskUseCase.Args> {
    data class Args(
        val id: String,
        val title: String? = null,
        val dueDate: LocalDate? = null,
        val isComplete: Boolean? = null
    ) : UseCase.Arguments()
}
