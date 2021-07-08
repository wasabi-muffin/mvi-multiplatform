package com.gmvalentino.addtask.modifiers

import com.gmvalentino.addtask.contract.AddTaskResult
import com.gmvalentino.modifiers.external.ExternalIntent
import com.gmvalentino.modifiers.external.ExternalIntentPublisher
import com.gmvalentino.modifiers.external.GlobalIntentWrapper
import kotlinx.coroutines.flow.receiveAsFlow

class AddTaskGlobalIntentDispatcher : ExternalIntentPublisher<AddTaskResult, ExternalIntent>(
    GlobalIntentWrapper.externalIntents
) {
    override fun transform(result: AddTaskResult): ExternalIntent? = when (result) {
        is AddTaskResult.TaskCreated -> ExternalIntent.TaskAdded(result.task)
        else -> null
    }
}