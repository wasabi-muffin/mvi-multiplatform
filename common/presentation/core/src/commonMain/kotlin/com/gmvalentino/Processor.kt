package com.gmvalentino

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class Processor<in STATE : State, ACTION : Action, RESULT : Result, EVENT : Event> {
    private val _events = Channel<EVENT>(UNLIMITED)
    val events: Flow<EVENT> = _events.receiveAsFlow()

    abstract suspend fun process(
        state: STATE,
        action: ACTION
    ): Flow<RESULT>

    suspend fun publish(event: EVENT) {
        _events.send(event)
    }
}
