package com.gmvalentino.remote

import com.gmvalentino.models.requests.AddTaskRequest
import com.gmvalentino.models.requests.EditTaskRequest
import com.gmvalentino.models.responses.AddTaskResponse
import com.gmvalentino.models.responses.EditTaskResponse
import com.gmvalentino.models.responses.GetTaskResponse

interface TaskRemoteDataSource {
    suspend fun getTasks(): GetTaskResponse
    suspend fun addTask(request: AddTaskRequest): AddTaskResponse
    suspend fun removeTask(id: String)
    suspend fun updateTask(id: String, request: EditTaskRequest): EditTaskResponse
}