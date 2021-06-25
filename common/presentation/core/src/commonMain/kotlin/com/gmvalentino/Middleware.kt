package com.gmvalentino

interface Middleware

fun interface IntentMiddleware : Middleware {
    fun transform(intent: Intent): Intent
}

fun interface ActionMiddleware : Middleware {
    fun transform(action: Action): Action
}

fun interface ResultMiddleware: Middleware {
    fun transform(result: Result): Result
}

fun interface StateMiddleware: Middleware {
    fun transform(state: State): State
}
