package com.gmvalentino.remote

import com.gmvalentino.models.TaskModel

interface TaskRemoteDataSource {
    suspend fun getTasks(): List<TaskModel>
    suspend fun addTask(task: TaskModel)
    suspend fun removeTask(id: String)
    suspend fun updateTask(id: String, isComplete: Boolean)
}