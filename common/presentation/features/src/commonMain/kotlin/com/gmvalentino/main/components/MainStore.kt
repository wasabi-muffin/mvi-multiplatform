package com.gmvalentino.main.components

import com.gmvalentino.components.BaseStore
import com.gmvalentino.components.Modifiers
import com.gmvalentino.main.contract.*
import com.gmvalentino.main.modifiers.ExternalIntentWrapper
import com.gmvalentino.main.modifiers.MainExternalIntentDispatcher
import com.gmvalentino.main.modifiers.MainExternalIntentSubscriber
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
            MainExternalIntentSubscriber(ExternalIntentWrapper)
        ),
        resultModifiers = listOf(
            ResultLogger(),
            MainExternalIntentDispatcher(ExternalIntentWrapper)
        ),
        stateModifiers = listOf(StateLogger())
    )
)