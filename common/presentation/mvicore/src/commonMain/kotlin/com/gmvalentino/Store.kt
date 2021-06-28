package com.gmvalentino

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Store<INTENT : Intent, STATE : State, EVENT : Event> {
    val state: StateFlow<STATE>
    val events: Flow<EVENT>
    fun dispatch(intent: INTENT)
    fun dispose()
    fun collect(
        onState: ((STATE) -> Unit),
        onEvent: ((EVENT) -> Unit)
    ): Job
}