package com.gmvalentino.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmvalentino.main.MainIntent
import com.gmvalentino.main.MainInterpreter
import com.gmvalentino.main.MainProcessor
import com.gmvalentino.main.MainReducer
import com.gmvalentino.main.MainStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import org.koin.core.component.KoinComponent

class MainViewModel(
    private val store: MainStore
) : ViewModel(), KoinComponent {

    val state = store.state.shareIn(viewModelScope, SharingStarted.Eagerly, 1)

    fun dispatch(intent: MainIntent) {
        store.dispatch(intent)
    }
}
