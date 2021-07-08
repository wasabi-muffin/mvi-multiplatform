package com.gmvalentino.overview.modifiers

import com.gmvalentino.modifiers.external.ExternalIntent
import com.gmvalentino.modifiers.external.ExternalIntentSubscriber
import com.gmvalentino.modifiers.external.GlobalIntentWrapper
import com.gmvalentino.overview.contract.OverviewAction

class OverviewGlobalIntentSubscriber: ExternalIntentSubscriber<OverviewAction, ExternalIntent>(
    GlobalIntentWrapper.externalIntents
) {
    override fun interpret(externalIntent: ExternalIntent): OverviewAction? {
        return when (externalIntent) {
            is ExternalIntent.TaskAdded -> OverviewAction.InsertTask(externalIntent.task)
        }
    }
}