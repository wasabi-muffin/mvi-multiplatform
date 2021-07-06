package com.gmvalentino.remote

import com.gmvalentino.models.BaseResponse
import com.gmvalentino.models.TaskModel

interface TaskRemoteDataSource {
    suspend fun getTasks(): List<TaskModel>
    suspend fun addTask(task: TaskModel): BaseResponse
    suspend fun removeTask(id: String): BaseResponse
    suspend fun updateTask(id: String, isComplete: Boolean): BaseResponse
}