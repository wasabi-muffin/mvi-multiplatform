package com.gmvalentino.android

import androidx.lifecycle.ViewModel
import com.gmvalentino.contract.Event
import com.gmvalentino.contract.Intent
import com.gmvalentino.contract.State
import com.gmvalentino.components.Store

open class BaseViewModel<in INTENT : Intent, out STATE : State, out EVENT : Event>(
    private val store: Store<INTENT, STATE, EVENT>
) : ViewModel() {
    val state = store.state
    val events = store.events
    fun dispatch(intent: INTENT) {
        store.dispatch(intent)
    }

    override fun onCleared() {
        store.dispose()
        super.onCleared()
    }
}
