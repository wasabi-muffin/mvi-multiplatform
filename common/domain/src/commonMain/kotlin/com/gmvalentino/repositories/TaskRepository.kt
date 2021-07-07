package com.gmvalentino.repositories

import com.gmvalentino.entities.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

interface TaskRepository {
    suspend fun getTasks(): List<Task>
    suspend fun addTask(title: String, dueDate: LocalDate): Task
    suspend fun deleteTask(id: String)
    suspend fun updateTask(id: String, title: String?, dueDate: LocalDate?, isComplete: Boolean?)
}