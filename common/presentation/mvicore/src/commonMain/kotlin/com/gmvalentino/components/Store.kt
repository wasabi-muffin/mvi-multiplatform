package com.gmvalentino.components

import com.gmvalentino.contract.Event
import com.gmvalentino.contract.Intent
import com.gmvalentino.contract.State
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * The store exposes a stream of [State] and a stream of [Event] for the client to react to.
 */
interface Store<INTENT : Intent, STATE : State, EVENT : Event> {
    /**
     * Stream of [State] exposed to the client
     */
    val state: StateFlow<STATE>

    /**
     * Stream of [Event] exposed to the client
     */
    val events: Flow<EVENT>

    /**
     * Dispatches an [Intent]
     */
    fun dispatch(intent: INTENT)

    /**
     * Cancels all jobs within the store
     */
    fun dispose()

    /**
     * Collect used for Kotlin Native
     */
    fun collect(
        onState: ((STATE) -> Unit),
        onEvent: ((EVENT) -> Unit)
    ): Job
}