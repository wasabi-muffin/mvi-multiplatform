package com.gmvalentino

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.engine.js.*

actual fun provideHttpClient(): HttpClient = HttpClient(Js)