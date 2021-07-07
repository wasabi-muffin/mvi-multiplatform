package com.gmvalentino.main.modifiers

import com.gmvalentino.modifiers.external.ExternalIntentPublisher
import com.gmvalentino.main.contract.MainResult

class MainExternalIntentDispatcher(
    externalIntentWrapper: ExternalIntentWrapper
) : ExternalIntentPublisher<MainResult, ExternalIntent>(
    externalIntentWrapper.externalIntents
) {
    override fun transform(result: MainResult): ExternalIntent? = when (result) {
        is MainResult.Deleted -> ExternalIntent
        else -> null
    }
}