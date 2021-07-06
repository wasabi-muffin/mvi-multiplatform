package com.gmvalentino.main.middlewares

import com.gmvalentino.modifiers.external.ExternalIntentDispatcherModifier
import com.gmvalentino.main.MainResult

class MainExternalIntentDispatcher(
    externalIntentWrapper: ExternalIntentWrapper
) : ExternalIntentDispatcherModifier<MainResult, ExternalIntent>(
    externalIntentWrapper.externalIntents
) {
    override fun resultInterpreter(result: MainResult): ExternalIntent? = when (result) {
        is MainResult.Deleted -> ExternalIntent
        else -> null
    }
}