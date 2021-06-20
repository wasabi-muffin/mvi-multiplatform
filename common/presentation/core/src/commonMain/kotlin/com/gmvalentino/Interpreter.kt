package com.gmvalentino

interface Interpreter<in INTENT: Intent, out ACTION: Action> {
    suspend fun interpret(intent: INTENT): ACTION
}
