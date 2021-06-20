package com.gmvalentino

import kotlinx.coroutines.flow.Flow

interface Processor<in STATE : State, ACTION : Action, out RESULT : Result> {
    suspend fun process(
        state: STATE,
        action: ACTION
    ): Flow<Processable>
}
