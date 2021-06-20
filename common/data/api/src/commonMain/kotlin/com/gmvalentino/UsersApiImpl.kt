package com.gmvalentino

import com.gmvalentino.models.UsersResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json

class UsersApiImpl(
    private val httpClient: HttpClient
) : UsersApi {

    override suspend fun getUsers(): UsersResponse = httpClient.request {
        url("https://randomuser.me/api")
        method = HttpMethod.Get
    }
}
