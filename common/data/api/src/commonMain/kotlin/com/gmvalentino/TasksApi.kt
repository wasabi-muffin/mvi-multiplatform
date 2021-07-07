package com.gmvalentino

import com.gmvalentino.models.requests.AddTaskRequest
import com.gmvalentino.models.requests.EditTaskRequest
import com.gmvalentino.models.responses.AddTaskResponse
import com.gmvalentino.models.responses.EditTaskResponse
import com.gmvalentino.models.responses.GetTaskResponse
import com.gmvalentino.remote.TaskRemoteDataSource
import io.ktor.client.request.*
import io.ktor.http.*

class TasksApi : TaskRemoteDataSource {
    override suspend fun getTasks(): GetTaskResponse =
        httpClientFactory().request {
            url("https://mvi-multiplatform-iohpvewgdq-du.a.run.app/tasks")
            method = HttpMethod.Get
        }

    override suspend fun addTask(request: AddTaskRequest): AddTaskResponse =
        httpClientFactory().request {
            url("https://mvi-multiplatform-iohpvewgdq-du.a.run.app/tasks")
            body = request
            method = HttpMethod.Post
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

    override suspend fun removeTask(id: String) =
        httpClientFactory().request<Unit> {
            url("https://mvi-multiplatform-iohpvewgdq-du.a.run.app/tasks/${id}")
            method = HttpMethod.Delete
        }

    override suspend fun updateTask(id: String, request: EditTaskRequest): EditTaskResponse =
        httpClientFactory().request {
            url("https://mvi-multiplatform-iohpvewgdq-du.a.run.app/tasks/${id}")
            method = HttpMethod.Put
            body = request
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
}