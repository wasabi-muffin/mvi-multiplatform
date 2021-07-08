package com.gmvalentino.components

import com.gmvalentino.contract.Action
import com.gmvalentino.contract.Intent
import com.gmvalentino.contract.Result
import com.gmvalentino.contract.State
import kotlinx.coroutines.flow.Flow

/**
 * [Modifier] apply transformations on a certain stream
 *
 * Input streams and output streams must be of the same type
 */
interface Modifier<Any> {
    fun apply(input: Flow<Any>): Flow<Any>
}

/**
 * [Modifier] for [Intent] streams
 */
fun interface IntentModifier<INTENT : Intent> : Modifier<INTENT> {
    override fun apply(input: Flow<INTENT>): Flow<INTENT>
}

/**
 * [Modifier] for [Action] streams
 */
fun interface ActionModifier<ACTION : Action> : Modifier<ACTION> {
    override fun apply(input: Flow<ACTION>): Flow<ACTION>
}

/**
 * [Modifier] for [Result] streams
 */
fun interface ResultModifier<RESULT : Result> : Modifier<RESULT> {
    override fun apply(input: Flow<RESULT>): Flow<RESULT>
}

/**
 * [Modifier] for [State] streams
 */
fun interface StateModifier<STATE : State> : Modifier<STATE> {
    override fun apply(input: Flow<STATE>): Flow<STATE>
}