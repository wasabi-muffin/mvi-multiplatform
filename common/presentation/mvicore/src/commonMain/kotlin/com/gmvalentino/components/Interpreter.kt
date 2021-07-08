package com.gmvalentino.components

import com.gmvalentino.contract.Action
import com.gmvalentino.contract.Intent

/**
 * Interpreters receive [Intent] from user input and maps it to an [Action]
 */
interface Interpreter<in INTENT: Intent, out ACTION: Action> {
    suspend fun interpret(intent: INTENT): ACTION
}