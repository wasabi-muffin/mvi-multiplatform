package com.gmvalentino.main.modifiers

import com.gmvalentino.entities.Task
import com.gmvalentino.modifiers.external.ExternalIntentSubscriber
import com.gmvalentino.main.contract.MainAction
import kotlinx.datetime.LocalDateTime

class MainExternalIntentSubscriber(
    externalIntentWrapper: ExternalIntentWrapper
) : ExternalIntentSubscriber<MainAction, ExternalIntent>(
    externalIntentWrapper.externalIntents
) {
    override fun interpret(externalIntent: ExternalIntent): MainAction? {
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