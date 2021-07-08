package com.gmvalentino.backend

import com.gmvalentino.models.TaskModel
import com.gmvalentino.models.requests.AddTaskRequest
import com.gmvalentino.models.requests.EditTaskRequest
import com.gmvalentino.models.responses.AddTaskResponse
import com.gmvalentino.models.responses.EditTaskResponse
import com.gmvalentino.models.responses.GetTaskResponse
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import java.util.*

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
            val now = Clock.System.todayAt(TimeZone.UTC)

            get("/tasks") {
                val result = listOf(
                    TaskModel(
                        UUID.randomUUID().toString(),
                        "Title 1",
                        now.plus(-1, DateTimeUnit.DAY),
                        true
                    ),
                    TaskModel(
                        UUID.randomUUID().toString(),
                        "Title 2",
                        now,
                        false
                    ),
                    TaskModel(
                        UUID.randomUUID().toString(),
                        "Title 3",
                        now.plus(1, DateTimeUnit.DAY),
                        false
                    ),
                    TaskModel(
                        UUID.randomUUID().toString(),
                        "Title 4",
                        now.plus(2, DateTimeUnit.DAY),
                        false
                    )
                )
                call.respond(GetTaskResponse(result))
            }
            post("/tasks") {
                val request = call.receive<AddTaskRequest>()
                call.respond(
                    AddTaskResponse(
                        TaskModel(
                            UUID.randomUUID().toString(),
                            request.title,
                            request.dueDate,
                            false
                        )
                    )
                )
            }
            delete("/tasks/{id}") {
                call.respondText("{}")
            }
            put("/tasks/{id}") {
                val id = call.parameters["id"] ?: ""
                val request = call.receive<EditTaskRequest>()
                call.respond(
                    EditTaskResponse(
                        TaskModel(
                            id,
                            request.title ?: "Title $id Changed",
                            now.plus(-2, DateTimeUnit.DAY),
                            request.isComplete ?: false
                        )
                    )
                )
            }
        }
    }.start(wait = true)
}
