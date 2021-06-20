package com.gmvalentino.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmvalentino.main.MainIntent
import com.gmvalentino.main.MainInterpreter
import com.gmvalentino.main.MainProcessor
import com.gmvalentino.main.MainReducer
import com.gmvalentino.main.MainStore
import com.gmvalentino.second.SecondIntent
import com.gmvalentino.second.SecondStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import org.koin.core.component.KoinComponent

class SecondViewModel(
    private val store: SecondStore
) : ViewModel(), KoinComponent {

    val state = store.state.shareIn(viewModelScope, SharingStarted.Eagerly, 1)

    fun dispatch(intent: SecondIntent) {
        store.dispatch(intent)
    }
}
