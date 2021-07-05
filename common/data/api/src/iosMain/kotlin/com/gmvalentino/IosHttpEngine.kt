package com.gmvalentino

import io.ktor.client.HttpClient
import io.ktor.client.engine.ios.*

actual fun provideHttpClient(): HttpClient = HttpClient(Ios)