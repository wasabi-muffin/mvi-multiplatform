package com.gmvalentino.modifiers

import co.touchlab.kermit.Kermit
import com.gmvalentino.components.ActionModifier
import com.gmvalentino.components.IntentModifier
import com.gmvalentino.components.ResultModifier
import com.gmvalentino.components.StateModifier
import com.gmvalentino.contract.Action
import com.gmvalentino.contract.Intent
import com.gmvalentino.contract.Result
import com.gmvalentino.contract.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

/**
 * Implementations of [Modifier] that logs events of that type
 */
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