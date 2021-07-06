package com.gmvalentino.main.middlewares

import com.gmvalentino.entities.Task
import com.gmvalentino.modifiers.external.ExternalIntentListenerModifier
import com.gmvalentino.main.MainAction
import kotlinx.datetime.LocalDateTime

class MainExternalIntentListener(
    externalIntentWrapper: ExternalIntentWrapper
) : ExternalIntentListenerModifier<MainAction, ExternalIntent>(
    externalIntentWrapper.externalIntents
) {
    override fun externalIntentInterpreter(externalIntent: ExternalIntent): MainAction? {
        return MainAction.Create(
            Task(
                id = (0..Int.MAX_VALUE).random().toString(),
                title = "External",
                details = "External",
                date = LocalDateTime(1, 1, 1, 1, 1, 1, 1),
                isComplete = false
            )
        )
    }
}