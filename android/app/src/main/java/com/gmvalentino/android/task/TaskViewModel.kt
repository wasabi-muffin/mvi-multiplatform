package com.gmvalentino.android.task

import com.gmvalentino.android.BaseViewModel
import com.gmvalentino.main.contract.MainEvent
import com.gmvalentino.main.contract.MainIntent
import com.gmvalentino.main.contract.MainState
import com.gmvalentino.main.components.MainStore

class TaskViewModel(
    store: MainStore
) : BaseViewModel<MainIntent, MainState, MainEvent>(store)
