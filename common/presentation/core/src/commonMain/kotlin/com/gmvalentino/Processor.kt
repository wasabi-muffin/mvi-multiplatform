package com.gmvalentino

import kotlinx.coroutines.flow.Flow

interface Processor<in STATE : State, ACTION : Action, RESULT : Result, EVENT : Event> {
    val events: Flow<EVENT>

    suspend fun process(
        state: STATE,
        action: ACTION
    ): Flow<RESULT>

    suspend fun publish(event: EVENT)
}