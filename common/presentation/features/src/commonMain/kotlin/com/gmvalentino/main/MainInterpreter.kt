package com.gmvalentino.main

import com.gmvalentino.Interpreter

class MainInterpreter : Interpreter<MainIntent, MainAction> {
    override suspend fun interpret(intent: MainIntent): MainAction = when (intent) {
        is MainIntent.TaskClicked -> MainAction.Toggle(intent.id)
        is MainIntent.CreateClicked -> MainAction.Create(intent.task)
        is MainIntent.DeleteClicked -> MainAction.Remove(intent.id)
        MainIntent.ResolveErrorClicked -> MainAction.ResolveError
    }
}
