package com.gmvalentino.main

import com.gmvalentino.BaseStore
import com.gmvalentino.Loader
import com.gmvalentino.Middleware
import com.gmvalentino.transformers.LoggingMiddleware

class MainStore(
    interpreter: MainInterpreter,
    processor: MainProcessor,
    reducer: MainReducer,
    vararg middlewares: Middleware
) : BaseStore<MainIntent, MainAction, MainResult, MainState, MainEvent>(
    initialState = MainState(),
    interpreter = interpreter,
    reducer = reducer,
    processor = processor,
    loaders = Loader(MainAction.LoadTasks),
    middlewares = arrayOf(LoggingMiddleware)
)
