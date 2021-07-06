package com.gmvalentino.repository

import com.gmvalentino.entities.Task
import com.gmvalentino.local.TaskLocalDataSource
import com.gmvalentino.mapper.TaskMapper
import com.gmvalentino.remote.TaskRemoteDataSource
import com.gmvalentino.repositories.TaskRepository

class TaskDataRepository(
    private val localDataSource: TaskLocalDataSource,
    private val remoteDataSource: TaskRemoteDataSource
) : TaskRepository {
    override suspend fun getTasks(): List<Task> {
        val tasks = remoteDataSource.getTasks()
        localDataSource.saveTasks(tasks)
        return tasks.map(TaskMapper::modelToEntity)
    }

    override suspend fun addTask(task: Task) {
        val taskModel = TaskMapper.entityToModel(task)
        remoteDataSource.addTask(taskModel)
        localDataSource.addTask(taskModel)
    }

    override suspend fun deleteTask(id: String) {
        remoteDataSource.removeTask(id)
        localDataSource.removeTask(id)
    }

    override suspend fun updateTask(id: String, isComplete: Boolean) {
        remoteDataSource.updateTask(id, isComplete)
        localDataSource.updateTask(id, isComplete)
    }
}