package com.gmvalentino.modifiers

import co.touchlab.kermit.Kermit
import com.gmvalentino.Action
import com.gmvalentino.Intent
import com.gmvalentino.Result
import com.gmvalentino.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class StateLogger<STATE : State> : StateModifier<STATE> {
    override fun apply(input: Flow<STATE>): Flow<STATE> = input.onEach {
        Kermit(defaultTag = "State").d { "$it" }
    }
}

class ActionLogger<ACTION : Action> : ActionModifier<ACTION> {
    override fun apply(input: Flow<ACTION>): Flow<ACTION> = input.onEach {
        Kermit(defaultTag = "Action").d { "$it" }
    }
}

class IntentLogger<INTENT : Intent> : IntentModifier<INTENT> {
    override fun apply(input: Flow<INTENT>): Flow<INTENT> = input.onEach {
        Kermit(defaultTag = "Intent").d { "$it" }
    }
}

class ResultLogger<RESULT : Result> : ResultModifier<RESULT> {
    override fun apply(input: Flow<RESULT>): Flow<RESULT> = input.onEach {
        Kermit(defaultTag = "Result").d { "$it" }
    }
}