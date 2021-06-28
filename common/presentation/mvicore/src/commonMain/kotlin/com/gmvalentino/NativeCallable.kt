package com.gmvalentino

import kotlinx.coroutines.Job

abstract class NativeScopable<
    out STATE : State,
    out EVENT : Event> {

    /**
     * Intended to use from iOS.
     * Do not use from Android.
     * Please use [Flow.collect] for each flows instead of using this on Android.
     */
    abstract fun collect(
        onState: ((STATE) -> Unit)? = null,
        onEvent: ((EVENT) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
    ): Job
}
