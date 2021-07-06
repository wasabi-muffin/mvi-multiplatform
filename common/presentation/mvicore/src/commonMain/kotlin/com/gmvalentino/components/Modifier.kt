package com.gmvalentino.components

import com.gmvalentino.contract.Action
import com.gmvalentino.contract.Intent
import com.gmvalentino.contract.Result
import com.gmvalentino.contract.State
import kotlinx.coroutines.flow.Flow

interface Modifier<Any> {
    fun apply(input: Flow<Any>): Flow<Any>
}

fun interface IntentModifier<INTENT : Intent> : Modifier<INTENT> {
    override fun apply(input: Flow<INTENT>): Flow<INTENT>
}

fun interface ActionModifier<ACTION : Action> : Modifier<ACTION> {
    override fun apply(input: Flow<ACTION>): Flow<ACTION>
}

fun interface ResultModifier<RESULT : Result> : Modifier<RESULT> {
    override fun apply(input: Flow<RESULT>): Flow<RESULT>
}

fun interface StateModifier<STATE : State> : Modifier<STATE> {
    override fun apply(input: Flow<STATE>): Flow<STATE>
}