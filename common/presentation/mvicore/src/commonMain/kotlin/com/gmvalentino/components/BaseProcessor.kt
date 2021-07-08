package com.gmvalentino.components

import com.gmvalentino.contract.Action
import com.gmvalentino.contract.Event
import com.gmvalentino.contract.Result
import com.gmvalentino.contract.State
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseProcessor<in STATE : State, ACTION : Action, RESULT : Result, EVENT : Event> :
    Processor<STATE, ACTION, RESULT, EVENT> {

    // ref: https://proandroiddev.com/android-singleliveevent-redux-with-kotlin-flow-b755c70bb055
    private val _events = Channel<EVENT>(Channel.BUFFERED)
    override val events: Flow<EVENT> = _events.receiveAsFlow()

    override suspend fun publish(event: EVENT) {
        _events.send(event)
    }
}