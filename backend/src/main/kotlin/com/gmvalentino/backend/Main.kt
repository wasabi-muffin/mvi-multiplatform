package com.gmvalentino.backend

import com.gmvalentino.models.TaskModel
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.put
import io.ktor.routing.routing
import io.ktor.serialization.json
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
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
            get("/tasks") {
                val timeZone = TimeZone.currentSystemDefault()
                val now = Clock.System.now()
                val result = listOf(
                    TaskModel(
                        "1",
                        "Title 1",
                        "Description 1",
                        now.plus(-1, DateTimeUnit.DAY, timeZone).toLocalDateTime(timeZone),
                        true
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
                        false
                    )
                )
                call.respond(result)
            }
            post("/tasks") {
                call.respondText("{}")
            }
            delete("/tasks") {
                call.respondText("{}")
            }
            put("/tasks") {
                call.respondText("{}")
            }
        }
    }.start(wait = true)
}
