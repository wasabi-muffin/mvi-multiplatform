package com.gmvalentino.usecases

import com.gmvalentino.entities.Task
import com.gmvalentino.repositories.TaskRepository

class GetTasks(
    private val repository: TaskRepository
) : GetTasksUseCase {
    override suspend fun execute(arguments: UseCase.None): List<Task> {
        return repository.getTasks()
    }
}

interface GetTasksUseCase : UseCase<List<Task>, UseCase.None>
