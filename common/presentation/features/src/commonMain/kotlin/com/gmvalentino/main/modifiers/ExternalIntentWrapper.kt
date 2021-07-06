package com.gmvalentino.main.modifiers

import com.gmvalentino.contract.Intent
import kotlinx.coroutines.flow.MutableSharedFlow

object ExternalIntentWrapper {
    val externalIntents = MutableSharedFlow<ExternalIntent>()
}

object ExternalIntent : Intent