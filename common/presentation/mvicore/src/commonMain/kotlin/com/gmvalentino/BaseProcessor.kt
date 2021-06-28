package com.gmvalentino

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseProcessor<in STATE : State, ACTION : Action, RESULT : Result, EVENT : Event> :
    Processor<STATE, ACTION, RESULT, EVENT> {

    private val _events = Channel<EVENT>(UNLIMITED)
    override val events: Flow<EVENT> = _events.receiveAsFlow()

    override suspend fun publish(event: EVENT) {
        _events.send(event)
    }
}
