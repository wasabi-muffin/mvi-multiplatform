package com.gmvalentino.backend

import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.serialization.json
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import com.gmvalentino.models.TestModel
import com.gmvalentino.models.TaskModel
import io.ktor.application.call
import io.ktor.response.respond
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

fun main() {
    val port = System.getenv().getOrDefault("PORT", "8080").toInt()
    embeddedServer(Netty, port) {
        install(ContentNegotiation) {
            json()
        }

        install(CORS) {
            method(HttpMethod.Options)
            method(HttpMethod.Put)
            method(HttpMethod.Delete)
            method(HttpMethod.Patch)
            header(HttpHeaders.Authorization)
            header(HttpHeaders.ContentType)
            header(HttpHeaders.AccessControlAllowOrigin)
            // header("any header") if you want to add any header
            allowCredentials = true
            allowNonSimpleContentTypes = true
            anyHost()
        }

        routing {
            get("/test") {
                val result = listOf(
                    TestModel("Marco", "gmvalentino8@gmail.com"),
                    TestModel("User 2", "user2@test.com"),
                    TestModel("User 3", "user3@test.com")
                )
                call.respond(result)
            }
            get("/tasks") {
                val timeZone = TimeZone.currentSystemDefault()
                val now = Clock.System.now()
                val result = listOf(
                    TaskModel(
                        "1",
                        "Title 1",
                        "Description 1",
                        now.plus(-1, DateTimeUnit.DAY, timeZone).toLocalDateTime(timeZone),
                        false
                    ),
                    TaskModel(
                        "2",
                        "Title 2",
                        "Description 2",
                        now.plus(-2, DateTimeUnit.DAY, timeZone).toLocalDateTime(timeZone),
                        false
                    ),
                    TaskModel(
                        "3",
                        "Title 3",
                        "Description 3",
                        now.plus(-3, DateTimeUnit.DAY, timeZone).toLocalDateTime(timeZone),
                        false
                    ),
                    TaskModel(
                        "4",
                        "Title 4",
                        "Description 4",
                        now.plus(-4, DateTimeUnit.DAY, timeZone).toLocalDateTime(timeZone),
                        true
                    )
                )
                call.respond(result)
            }
        }
    }.start(wait = true)
}
