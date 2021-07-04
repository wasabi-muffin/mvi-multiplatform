package com.gmvalentino.android

import androidx.lifecycle.ViewModel
import com.gmvalentino.Event
import com.gmvalentino.Intent
import com.gmvalentino.State
import com.gmvalentino.Store

open class BaseViewModel<in INTENT : Intent, out STATE : State, out EVENT : Event>(
    private val store: Store<INTENT, STATE, EVENT>
) : ViewModel() {
    val state = store.state
    val events = store.events
    fun dispatch(intent: INTENT) {
        store.dispatch(intent)
    }
}
