package com.gmvalentino

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

interface Store<INTENT : Intent, STATE : State, EVENT : Event> {
    val state: Flow<STATE>
    val events: Flow<EVENT>
    fun dispatch(intent: INTENT)
    fun dispose()
    fun collect(
        onState: ((STATE) -> Unit),
        onEvent: ((EVENT) -> Unit)
    ): Job
}