package com.gmvalentino.addtask.components

import com.gmvalentino.addtask.contract.*
import com.gmvalentino.components.BaseStore
import com.gmvalentino.components.Modifiers
import com.gmvalentino.modifiers.ActionLogger
import com.gmvalentino.modifiers.IntentLogger
import com.gmvalentino.modifiers.ResultLogger
import com.gmvalentino.modifiers.StateLogger

class AddTaskStore(
    interpreter: AddTaskInterpreter,
    processor: AddTaskProcessor,
    reducer: AddTaskReducer
) : BaseStore<AddTaskIntent, AddTaskAction, AddTaskResult, AddTaskState, AddTaskEvent>(
    initialState = AddTaskState(),
    interpreter = interpreter,
    processor = processor,
    reducer = reducer,
    modifiers = Modifiers(
        intentModifiers = listOf(
            IntentLogger()
        ),
        actionModifiers = listOf(
            ActionLogger()
        ),
        resultModifiers = listOf(
            ResultLogger()
        ),
        stateModifiers = listOf(
            StateLogger()
        )
    )
)