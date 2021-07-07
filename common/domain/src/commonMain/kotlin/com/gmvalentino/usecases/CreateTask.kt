package com.gmvalentino.usecases

import com.gmvalentino.entities.Task
import com.gmvalentino.repositories.TaskRepository
import kotlinx.datetime.LocalDate

class CreateTask(
    private val repository: TaskRepository
) : CreateTaskUseCase {
    override suspend fun execute(arguments: CreateTaskUseCase.Args): Task {
        return repository.addTask(arguments.title, arguments.dueDate)
    }
}

interface CreateTaskUseCase : UseCase<Task, CreateTaskUseCase.Args> {
    data class Args(
        val title: String,
        val dueDate: LocalDate
    ) : UseCase.Arguments()
}
