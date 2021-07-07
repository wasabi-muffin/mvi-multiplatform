package com.gmvalentino.main.modifiers

import com.gmvalentino.main.contract.MainAction
import com.gmvalentino.modifiers.external.ExternalIntentSubscriber
import kotlinx.datetime.LocalDate

class MainExternalIntentSubscriber(
    externalIntentWrapper: ExternalIntentWrapper
) : ExternalIntentSubscriber<MainAction, ExternalIntent>(
    externalIntentWrapper.externalIntents
) {
    override fun interpret(externalIntent: ExternalIntent): MainAction? {
        return MainAction.LoadTasks
    }
}