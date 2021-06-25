package com.gmvalentino

import kotlinx.coroutines.flow.Flow

interface Store<INTENT: Intent> {
    val state: Flow<State>
    val events: Flow<Event>
    fun dispatch(intent: INTENT)
    fun dispose()
}