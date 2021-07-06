package com.gmvalentino.modifiers.external

import com.gmvalentino.contract.Intent
import com.gmvalentino.contract.Result
import com.gmvalentino.components.ResultModifier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach

abstract class ExternalIntentDispatcherModifier<RESULT : Result, INTENT : Intent>(
    private val externalIntents: MutableSharedFlow<INTENT>,
) : ResultModifier<RESULT> {
    override fun apply(input: Flow<RESULT>): Flow<RESULT> = input.onEach { result ->
        resultInterpreter(result)?.let { externalIntents.emit(it) }
    }

    abstract fun resultInterpreter(result: RESULT): INTENT?
}