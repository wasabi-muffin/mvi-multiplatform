package com.gmvalentino.main

import com.gmvalentino.Interpreter

class MainInterpreter : Interpreter<MainIntent, MainAction> {
    override suspend fun interpret(intent: MainIntent): MainAction = when (intent) {
        is MainIntent.Toggle -> MainAction.Toggle(intent.id)
    }
}
