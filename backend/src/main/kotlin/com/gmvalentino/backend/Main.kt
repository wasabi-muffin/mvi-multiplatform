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
import io.ktor.application.call
import io.ktor.response.respond

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
        }
    }.start(wait = true)
}
