package com.gmvalentino.repository

import com.gmvalentino.entities.Task
import com.gmvalentino.local.TaskLocalDataSource
import com.gmvalentino.mapper.TaskMapper
import com.gmvalentino.models.requests.AddTaskRequest
import com.gmvalentino.models.requests.EditTaskRequest
import com.gmvalentino.remote.TaskRemoteDataSource
import com.gmvalentino.repositories.TaskRepository
import kotlinx.datetime.LocalDate

class TaskDataRepository(
    private val localDataSource: TaskLocalDataSource,
    private val remoteDataSource: TaskRemoteDataSource
) : TaskRepository {
    override suspend fun getTasks(): List<Task> {
        val response = remoteDataSource.getTasks()
        localDataSource.saveTasks(response.tasks)
        return response.tasks.map(TaskMapper::modelToEntity)
    }

    override suspend fun addTask(
        title: String,
        dueDate: LocalDate
    ): Task = runCatching {
        remoteDataSource.addTask(AddTaskRequest(title, dueDate))
    }.fold(
        onSuccess = {
            localDataSource.addTask(it.task)
            it.task.let(TaskMapper::modelToEntity)
        },
        onFailure = {
            throw it
        }
    )

    override suspend fun deleteTask(id: String) {
        remoteDataSource.removeTask(id)
        localDataSource.removeTask(id)
    }

    override suspend fun updateTask(
        id: String,
        title: String?,
        dueDate: LocalDate?,
        isComplete: Boolean?
    ): Unit = runCatching {
        remoteDataSource.updateTask(id, EditTaskRequest(title, dueDate, isComplete))
    }.fold(
        onSuccess = {
            localDataSource.updateTask(it.task)
            it.task.let(TaskMapper::modelToEntity)
        },
        onFailure = {
            throw it
        }
    )
}