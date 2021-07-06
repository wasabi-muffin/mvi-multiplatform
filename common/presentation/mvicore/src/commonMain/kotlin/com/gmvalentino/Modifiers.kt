package com.gmvalentino

import com.gmvalentino.modifiers.ActionModifier
import com.gmvalentino.modifiers.IntentModifier
import com.gmvalentino.modifiers.ResultModifier
import com.gmvalentino.modifiers.StateModifier

class Modifiers<INTENT : Intent, ACTION : Action, RESULT : Result, STATE : State>(
    val intentModifiers: List<IntentModifier<INTENT>> = emptyList(),
    val actionModifiers: List<ActionModifier<ACTION>> = emptyList(),
    val resultModifiers: List<ResultModifier<RESULT>> = emptyList(),
    val stateModifiers: List<StateModifier<STATE>> = emptyList()
)
