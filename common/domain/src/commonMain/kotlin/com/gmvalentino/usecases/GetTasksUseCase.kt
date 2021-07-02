package com.gmvalentino.usecases

import com.gmvalentino.entities.Task
import com.gmvalentino.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase(
    private val repository: TaskRepository
) : GetTasksUseCaseProtocol {
    override suspend fun execute(arguments: UseCase.None): Flow<List<Task>> {
        return repository.getTasks()
    }
}

interface GetTasksUseCaseProtocol : UseCase<Flow<List<Task>>, UseCase.None>
