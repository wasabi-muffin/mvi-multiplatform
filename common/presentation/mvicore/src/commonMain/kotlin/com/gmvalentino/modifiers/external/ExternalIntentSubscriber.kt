package com.gmvalentino.modifiers.external

import com.gmvalentino.contract.Action
import com.gmvalentino.components.ActionModifier
import com.gmvalentino.contract.Intent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

abstract class ExternalIntentSubscriber<ACTION : Action, INTENT : Intent>(
    private val intents: Flow<INTENT>,
) : ActionModifier<ACTION> {
    override fun apply(input: Flow<ACTION>): Flow<ACTION> = merge(
        input, intents.map(::interpret).filterNotNull()
    )

    abstract fun interpret(externalIntent: INTENT): ACTION?
}