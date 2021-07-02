package com.gmvalentino.android

import androidx.lifecycle.ViewModel
import com.gmvalentino.Event
import com.gmvalentino.Intent
import com.gmvalentino.State
import com.gmvalentino.Store

open class BaseViewModel<INTENT : Intent, STATE : State, EVENT : Event>(
    private val store: Store<INTENT, STATE, EVENT>
) : Store<INTENT, STATE, EVENT> by store, ViewModel() {
    override fun onCleared() {
        dispose()
        super.onCleared()
    }
}
