package com.gmvalentino.components

import com.gmvalentino.contract.Action
import com.gmvalentino.contract.Intent
import com.gmvalentino.contract.Result
import com.gmvalentino.contract.State

/**
 * Container for different [Modifier]s
 *
 * TODO: Reconsider Architecture
 */
class Modifiers<INTENT : Intent, ACTION : Action, RESULT : Result, STATE : State>(
    val intentModifiers: List<IntentModifier<INTENT>> = emptyList(),
    val actionModifiers: List<ActionModifier<ACTION>> = emptyList(),
    val resultModifiers: List<ResultModifier<RESULT>> = emptyList(),
    val stateModifiers: List<StateModifier<STATE>> = emptyList()
)