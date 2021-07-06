package com.gmvalentino.transformers

import co.touchlab.kermit.Kermit
import com.gmvalentino.Action
import com.gmvalentino.ActionMiddleware
import com.gmvalentino.Intent
import com.gmvalentino.IntentMiddleware
import com.gmvalentino.Result
import com.gmvalentino.ResultMiddleware
import com.gmvalentino.State
import com.gmvalentino.StateMiddleware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class StateLoggingMiddleware<STATE : State> : StateMiddleware<STATE> {
    override fun apply(input: Flow<STATE>): Flow<STATE> = input.onEach {
        Kermit(defaultTag = "State").d { "$it" }
    }
}

class ActionLoggingMiddleware<ACTION : Action> : ActionMiddleware<ACTION> {
    override fun apply(input: Flow<ACTION>): Flow<ACTION> = input.onEach {
        Kermit(defaultTag = "Action").d { "$it" }
    }
}

class IntentLoggingMiddleware<INTENT : Intent> : IntentMiddleware<INTENT> {
    override fun apply(input: Flow<INTENT>): Flow<INTENT> = input.onEach {
        Kermit(defaultTag = "Intent").d { "$it" }
    }
}

class ResultLoggingMiddleware<RESULT : Result> : ResultMiddleware<RESULT> {
    override fun apply(input: Flow<RESULT>): Flow<RESULT> = input.onEach {
        Kermit(defaultTag = "Result").d { "$it" }
    }
}