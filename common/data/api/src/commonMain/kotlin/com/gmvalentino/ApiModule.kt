package com.gmvalentino

import com.gmvalentino.remote.TaskRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val apiModule = module {

    factory {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                        useArrayPolymorphism = true
                    }
                )
                accept(ContentType.Application.Json)
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    single<TaskRemoteDataSource> {
        TasksApi()
    }
}

expect fun provideHttpClient(): HttpClient

fun httpClientFactory(): HttpClient = HttpClient {
    install(JsonFeature) {
        serializer = KotlinxSerializer(
            Json {
                isLenient = true
                ignoreUnknownKeys = true
                useArrayPolymorphism = true
            }
        )
        accept(ContentType.Application.Json)
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
}