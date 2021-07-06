package com.gmvalentino.components

import com.gmvalentino.contract.Action
import com.gmvalentino.contract.Event
import com.gmvalentino.contract.Result
import com.gmvalentino.contract.State
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseProcessor<in STATE : State, ACTION : Action, RESULT : Result, EVENT : Event> :
    Processor<STATE, ACTION, RESULT, EVENT> {

    private val _events = MutableSharedFlow<EVENT>()
    override val events: SharedFlow<EVENT> = _events

    override suspend fun publish(event: EVENT) {
        _events.emit(event)
    }
}