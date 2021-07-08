package com.gmvalentino.modifiers

import com.gmvalentino.components.ActionModifier
import com.gmvalentino.contract.Action
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.merge

/**
 * Implementation of a modifier that loads [Action] when the [Store] is created
 */
class ActionLoader<ACTION : Action>(
    vararg val actions: ACTION
) : ActionModifier<ACTION> {
    override fun apply(input: Flow<ACTION>): Flow<ACTION> = merge(actions.asFlow(), input)
}