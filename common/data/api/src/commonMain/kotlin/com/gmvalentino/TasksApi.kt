package com.gmvalentino

import com.gmvalentino.models.TaskModel
import com.gmvalentino.remote.TaskRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class TasksApi(
    private val httpClient: HttpClient
) : TaskRemoteDataSource {
    override suspend fun getTasks(): List<TaskModel> = httpClient.request {
        url("https://mvi-multiplatform-iohpvewgdq-du.a.run.app/tasks")
        method = HttpMethod.Get
    }

    override suspend fun addTask(task: TaskModel) = httpClient.request<Unit> {
        url("https://mvi-multiplatform-iohpvewgdq-du.a.run.app/tasks")
        method = HttpMethod.Post
    }

    override suspend fun removeTask(id: String) = httpClient.request<Unit> {
        url("https://mvi-multiplatform-iohpvewgdq-du.a.run.app/tasks")
        method = HttpMethod.Delete
    }

    override suspend fun updateTask(
        id: String,
        isComplete: Boolean
    ) = httpClient.request<Unit> {
        url("https://mvi-multiplatform-iohpvewgdq-du.a.run.app/tasks")
        method = HttpMethod.Put
    }
}