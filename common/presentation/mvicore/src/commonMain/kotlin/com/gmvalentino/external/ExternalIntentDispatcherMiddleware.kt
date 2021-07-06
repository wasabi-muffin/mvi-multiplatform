package com.gmvalentino.external

import com.gmvalentino.Intent
import com.gmvalentino.Result
import com.gmvalentino.ResultMiddleware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach

abstract class ExternalIntentDispatcherMiddleware<RESULT : Result, INTENT : Intent>(
    private val externalIntents: MutableSharedFlow<INTENT>,
) : ResultMiddleware<RESULT> {
    override fun apply(input: Flow<RESULT>): Flow<RESULT> = input.onEach { result ->
        resultInterpreter(result)?.let { externalIntents.emit(it) }
    }

    abstract fun resultInterpreter(result: RESULT): INTENT?
}