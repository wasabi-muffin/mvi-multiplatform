package com.gmvalentino.main.middlewares

import com.gmvalentino.entities.Task
import com.gmvalentino.external.ExternalIntentListenerMiddleware
import com.gmvalentino.main.MainAction
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime

class MainExternalIntentListenerMiddleware(
    externalIntents: Flow<ExternalIntent>
) : ExternalIntentListenerMiddleware<MainAction, ExternalIntent>(externalIntents) {
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