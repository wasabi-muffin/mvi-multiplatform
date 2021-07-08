package com.gmvalentino.overview.components

import com.gmvalentino.components.BaseStore
import com.gmvalentino.components.Modifiers
import com.gmvalentino.modifiers.*
import com.gmvalentino.overview.contract.*
import com.gmvalentino.overview.modifiers.OverviewGlobalIntentSubscriber

class OverviewStore(
    interpreter: OverviewInterpreter,
    processor: OverviewProcessor,
    reducer: OverviewReducer
) : BaseStore<OverviewIntent, OverviewAction, OverviewResult, OverviewState, OverviewEvent>(
    initialState = OverviewState(),
    interpreter = interpreter,
    processor = processor,
    reducer = reducer,
    modifiers = Modifiers(
        intentModifiers = listOf(
            IntentLogger()
        ),
        actionModifiers = listOf(
            OverviewGlobalIntentSubscriber(),
            ActionLoader(OverviewAction.LoadTasks),
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