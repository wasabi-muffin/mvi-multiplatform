package com.gmvalentino.main

import com.gmvalentino.Loader
import com.gmvalentino.Store

class MainStore(
    interpreter: MainInterpreter,
    processor: MainProcessor,
    reducer: MainReducer
) : Store<MainIntent, MainAction, MainResult, MainState, MainEvent>(
    initialState = MainState(),
    interpreter = interpreter,
    reducer = reducer,
    processor = processor,
    loaders = Loader(MainAction.LoadTasks)
)
