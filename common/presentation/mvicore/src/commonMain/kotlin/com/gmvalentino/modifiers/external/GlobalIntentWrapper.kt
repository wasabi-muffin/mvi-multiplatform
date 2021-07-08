package com.gmvalentino.modifiers.external

import com.gmvalentino.contract.Intent
import com.gmvalentino.entities.Task
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * Using as example, use a class and inject instead
 * Delete later
 */
object GlobalIntentWrapper {
    val externalIntents = MutableSharedFlow<ExternalIntent>()
}

sealed class ExternalIntent : Intent {
    data class TaskAdded(val task: Task) : ExternalIntent()
}