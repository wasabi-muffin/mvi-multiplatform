package com.gmvalentino.main

import com.gmvalentino.components.BaseStore
import com.gmvalentino.components.Modifiers
import com.gmvalentino.main.middlewares.ExternalIntentWrapper
import com.gmvalentino.main.middlewares.MainExternalIntentDispatcher
import com.gmvalentino.main.middlewares.MainExternalIntentListener
import com.gmvalentino.modifiers.*

class MainStore(
    interpreter: MainInterpreter,
    processor: MainProcessor,
    reducer: MainReducer,
) : BaseStore<MainIntent, MainAction, MainResult, MainState, MainEvent>(
    initialState = MainState(),
    interpreter = interpreter,
    reducer = reducer,
    processor = processor,
    modifiers = Modifiers(
        intentModifiers = listOf(IntentLogger()),
        actionModifiers = listOf(
            ActionLoader(MainAction.LoadTasks),
            ActionLogger(),
            MainExternalIntentListener(ExternalIntentWrapper)
        ),
        resultModifiers = listOf(
            ResultLogger(),
            MainExternalIntentDispatcher(ExternalIntentWrapper)
        ),
        stateModifiers = listOf(StateLogger())
    )
)