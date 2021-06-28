package com.gmvalentino.android.task

import com.gmvalentino.android.BaseViewModel
import com.gmvalentino.main.MainEvent
import com.gmvalentino.main.MainIntent
import com.gmvalentino.main.MainState
import com.gmvalentino.main.MainStore

class TaskViewModel(
    store: MainStore
) : BaseViewModel<MainIntent, MainState, MainEvent>(store)
