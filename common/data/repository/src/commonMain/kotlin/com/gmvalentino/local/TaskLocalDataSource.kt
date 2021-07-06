package com.gmvalentino.local

import com.gmvalentino.models.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskLocalDataSource {
    suspend fun getTasks(): List<TaskModel>
    suspend fun saveTasks(tasks: List<TaskModel>)
    suspend fun addTask(task: TaskModel)
    suspend fun removeTask(id: String)
    suspend fun updateTask(id: String, isComplete: Boolean)
}