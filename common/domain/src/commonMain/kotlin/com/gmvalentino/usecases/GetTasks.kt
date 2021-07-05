package com.gmvalentino.usecases

import com.gmvalentino.entities.Task
import com.gmvalentino.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasks(
    private val repository: TaskRepository
) : GetTasksUseCase {

    override suspend fun execute(arguments: UseCase.None): Flow<List<Task>> {
        return repository.getTasks()
    }
}

interface GetTasksUseCase : UseCase<Flow<List<Task>>, UseCase.None>
