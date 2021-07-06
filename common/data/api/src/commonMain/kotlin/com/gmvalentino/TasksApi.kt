package com.gmvalentino

import com.gmvalentino.models.BaseResponse
import com.gmvalentino.models.TaskModel
import com.gmvalentino.remote.TaskRemoteDataSource
import io.ktor.client.call.receive
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.statement.HttpStatement
import io.ktor.http.HttpMethod

class TasksApi : TaskRemoteDataSource {
    override suspend fun getTasks(): List<TaskModel> = httpClientFactory().request<HttpStatement> {
        url("https://mvi-multiplatform-iohpvewgdq-du.a.run.app/tasks")
        method = HttpMethod.Get
    }.execute {
        it.receive()
    }

    override suspend fun addTask(task: TaskModel): BaseResponse =
        httpClientFactory().request<HttpStatement> {
            url("https://mvi-multiplatform-iohpvewgdq-du.a.run.app/tasks")
            method = HttpMethod.Post
        }.execute {
            it.receive()
        }

    override suspend fun removeTask(id: String): BaseResponse =
        httpClientFactory().request<HttpStatement> {
            url("https://mvi-multiplatform-iohpvewgdq-du.a.run.app/tasks")
            method = HttpMethod.Delete
        }.execute {
            it.receive()
        }

    override suspend fun updateTask(
        id: String,
        isComplete: Boolean
    ): BaseResponse = httpClientFactory().request<HttpStatement> {
        url("https://mvi-multiplatform-iohpvewgdq-du.a.run.app/tasks")
        method = HttpMethod.Put
    }.execute {
        it.receive()
    }
}