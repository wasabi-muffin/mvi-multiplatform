package com.gmvalentino.modifiers.external

import com.gmvalentino.contract.Intent
import com.gmvalentino.entities.Task
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * Using as an example
 * TODO: Delete this
 */
object GlobalIntentWrapper {
    val externalIntents = MutableSharedFlow<ExternalIntent>()
}

sealed class ExternalIntent : Intent {
    data class TaskAdded(val task: Task) : ExternalIntent()
}