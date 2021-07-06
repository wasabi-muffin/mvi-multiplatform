package com.gmvalentino.main.middlewares

import com.gmvalentino.Intent
import kotlinx.coroutines.flow.MutableSharedFlow

object ExternalIntentWrapper {
    val externalIntents = MutableSharedFlow<ExternalIntent>()
}

object ExternalIntent : Intent