package com.gmvalentino.main

import com.gmvalentino.Interpreter

class MainInterpreter : Interpreter<MainIntent, MainAction> {
    override suspend fun interpret(intent: MainIntent): MainAction {
        return when (intent) {
            MainIntent.Increment -> MainAction.Add(1)
            MainIntent.Decrement -> MainAction.Add(-1)
        }
    }
}
