package com.gmvalentino.repositories

import com.gmvalentino.entities.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun getTasks(): List<Task>
    suspend fun addTask(task: Task)
    suspend fun deleteTask(id: String)
    suspend fun updateTask(id: String, isComplete: Boolean)
}