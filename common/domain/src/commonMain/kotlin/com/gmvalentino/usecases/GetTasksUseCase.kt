package com.gmvalentino.usecases

import com.gmvalentino.entities.Task
import com.gmvalentino.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase(
    private val repository: TaskRepository
) : GetTasksUseCaseProtocol() {
    override suspend fun task(arguments: None): Flow<List<Task>> {
        return repository.getTasks()
    }
}

abstract class GetTasksUseCaseProtocol : UseCase<Flow<List<Task>>, UseCase.None>()
