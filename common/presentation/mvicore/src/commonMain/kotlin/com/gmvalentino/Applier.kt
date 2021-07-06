package com.gmvalentino

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach

class Applier<INTENT : Intent, ACTION : Action, RESULT : Result, STATE : State>(
    val intentMiddlewares: List<IntentMiddleware<INTENT>> = emptyList(),
    val actionMiddlewares: List<ActionMiddleware<ACTION>> = emptyList(),
    val resultMiddlewares: List<ResultMiddleware<RESULT>> = emptyList(),
    val stateMiddlewares: List<StateMiddleware<STATE>> = emptyList()
)
