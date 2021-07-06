package com.gmvalentino.modifiers.external

import com.gmvalentino.Action
import com.gmvalentino.modifiers.ActionModifier
import com.gmvalentino.Intent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

abstract class ExternalIntentListenerModifier<ACTION : Action, INTENT : Intent>(
    private val externalIntents: Flow<INTENT>,
) : ActionModifier<ACTION> {
    override fun apply(input: Flow<ACTION>): Flow<ACTION> = merge(
        input, externalIntents.map { externalIntentInterpreter(it) }.filterNotNull()
    )

    abstract fun externalIntentInterpreter(externalIntent: INTENT): ACTION?
}