package com.gmvalentino.second

import com.gmvalentino.Loader
import com.gmvalentino.Store

class SecondStore(
    interpreter: SecondInterpreter,
    processor: SecondProcessor,
    reducer: SecondReducer
) : Store<SecondState, SecondIntent, SecondAction, SecondResult>(
    initialState = SecondState(),
    interpreter = interpreter,
    reducer = reducer,
    processor = processor,
    loaders = Loader(SecondAction.LoadCounter)
)
