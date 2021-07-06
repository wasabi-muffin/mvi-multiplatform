package com.gmvalentino.main

import com.gmvalentino.Applier
import com.gmvalentino.BaseStore
import com.gmvalentino.Loader
import com.gmvalentino.main.middlewares.ExternalIntentWrapper
import com.gmvalentino.main.middlewares.MainExternalIntentDispatcherMiddleware
import com.gmvalentino.main.middlewares.MainExternalIntentListenerMiddleware
import com.gmvalentino.transformers.ActionLoggingMiddleware
import com.gmvalentino.transformers.IntentLoggingMiddleware
import com.gmvalentino.transformers.ResultLoggingMiddleware
import com.gmvalentino.transformers.StateLoggingMiddleware

class MainStore(
    interpreter: MainInterpreter,
    processor: MainProcessor,
    reducer: MainReducer,
) : BaseStore<MainIntent, MainAction, MainResult, MainState, MainEvent>(
    initialState = MainState(),
    interpreter = interpreter,
    reducer = reducer,
    processor = processor,
    loaders = Loader(MainAction.LoadTasks),
    applier = Applier(
        intentMiddlewares = listOf(IntentLoggingMiddleware()),
        actionMiddlewares = listOf(
            ActionLoggingMiddleware(),
            MainExternalIntentListenerMiddleware(
                ExternalIntentWrapper.externalIntents
            )
        ),
        resultMiddlewares = listOf(
            ResultLoggingMiddleware(),
            MainExternalIntentDispatcherMiddleware(
                ExternalIntentWrapper.externalIntents
            )
        ),
        stateMiddlewares = listOf(StateLoggingMiddleware())
    )
)