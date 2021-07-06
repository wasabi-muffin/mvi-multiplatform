package com.gmvalentino.main.middlewares

import com.gmvalentino.external.ExternalIntentDispatcherMiddleware
import com.gmvalentino.main.MainResult
import kotlinx.coroutines.flow.MutableSharedFlow

class MainExternalIntentDispatcherMiddleware(
    externalIntents: MutableSharedFlow<ExternalIntent>
) : ExternalIntentDispatcherMiddleware<MainResult, ExternalIntent>(
    externalIntents
) {
    override fun resultInterpreter(result: MainResult): ExternalIntent? = when (result) {
        is MainResult.Deleted -> ExternalIntent
        else -> null
    }
}