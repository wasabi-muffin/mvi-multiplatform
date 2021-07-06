package com.gmvalentino

import kotlinx.coroutines.flow.Flow

interface Middleware<Any> {
    fun apply(input: Flow<Any>): Flow<Any>
}

fun interface IntentMiddleware<INTENT : Intent> : Middleware<INTENT> {
    override fun apply(input: Flow<INTENT>): Flow<INTENT>
}

fun interface ActionMiddleware<ACTION : Action> : Middleware<ACTION> {
    override fun apply(input: Flow<ACTION>): Flow<ACTION>
}

fun interface ResultMiddleware<RESULT : Result> : Middleware<RESULT> {
    override fun apply(input: Flow<RESULT>): Flow<RESULT>
}

fun interface StateMiddleware<STATE : State> : Middleware<STATE> {
    override fun apply(input: Flow<STATE>): Flow<STATE>
}