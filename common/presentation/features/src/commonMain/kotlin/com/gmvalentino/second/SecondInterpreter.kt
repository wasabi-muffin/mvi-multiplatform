package com.gmvalentino.second

import com.gmvalentino.Interpreter

class SecondInterpreter : Interpreter<SecondIntent, SecondAction> {
    override suspend fun interpret(intent: SecondIntent): SecondAction {
        return when (intent) {
            SecondIntent.Increment -> SecondAction.Add(2)
            SecondIntent.Decrement -> SecondAction.Add(-2)
        }
    }
}
