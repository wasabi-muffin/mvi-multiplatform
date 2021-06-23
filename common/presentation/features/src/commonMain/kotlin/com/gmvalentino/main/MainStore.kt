package com.gmvalentino.main

import com.gmvalentino.Loader
import com.gmvalentino.Store

class MainStore(
    interpreter: MainInterpreter,
    processor: MainProcessor,
    reducer: MainReducer
) : Store<MainState, MainIntent, MainAction, MainResult>(
    initialState = MainState(),
    interpreter = interpreter,
    reducer = reducer,
    processor = processor,
    loaders = Loader(MainAction.LoadTasks)
)
