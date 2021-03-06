package com.gmvalentino.modifiers.external

import com.gmvalentino.contract.Intent
import com.gmvalentino.contract.Result
import com.gmvalentino.components.ResultModifier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach

/**
 * Abstract implementation of [Modifier] that publishes [Intent] to an external source
 *
 * Use this together with [ExternalIntentSubscriber] to communicate between [Store]s
 */
abstract class ExternalIntentPublisher<RESULT : Result, INTENT : Intent>(
    private val intents: MutableSharedFlow<INTENT>,
) : ResultModifier<RESULT> {
    override fun apply(input: Flow<RESULT>): Flow<RESULT> = input.onEach { result ->
        transform(result)?.let { intents.emit(it) }
    }

    abstract fun transform(result: RESULT): INTENT?
}