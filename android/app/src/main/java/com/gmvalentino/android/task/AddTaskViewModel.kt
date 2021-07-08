package com.gmvalentino.android.task

import com.gmvalentino.addtask.components.AddTaskStore
import com.gmvalentino.addtask.contract.AddTaskEvent
import com.gmvalentino.addtask.contract.AddTaskIntent
import com.gmvalentino.addtask.contract.AddTaskState
import com.gmvalentino.android.BaseViewModel

class AddTaskViewModel(
    store: AddTaskStore
) : BaseViewModel<AddTaskIntent, AddTaskState, AddTaskEvent>(store)