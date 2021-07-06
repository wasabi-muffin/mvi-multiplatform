package com.gmvalentino.components

import com.gmvalentino.contract.Action
import com.gmvalentino.contract.Intent

interface Interpreter<in INTENT: Intent, out ACTION: Action> {
    suspend fun interpret(intent: INTENT): ACTION
}