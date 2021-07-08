package com.gmvalentino

import com.gmvalentino.remote.TaskRemoteDataSource
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
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