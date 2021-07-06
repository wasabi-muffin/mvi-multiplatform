package com.gmvalentino.external

import com.gmvalentino.Action
import com.gmvalentino.ActionMiddleware
import com.gmvalentino.Intent
import com.gmvalentino.IntentMiddleware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

abstract class ExternalIntentListenerMiddleware<ACTION : Action, INTENT : Intent>(
    private val externalIntents: Flow<INTENT>,
) : ActionMiddleware<ACTION> {
    override fun apply(input: Flow<ACTION>): Flow<ACTION> = merge(
        input, externalIntents.map { externalIntentInterpreter(it) }.filterNotNull()
    )

    abstract fun externalIntentInterpreter(externalIntent: INTENT): ACTION?
}