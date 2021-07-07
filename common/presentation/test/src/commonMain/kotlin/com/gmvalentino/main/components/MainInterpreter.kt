package com.gmvalentino.main.components

import com.gmvalentino.components.Interpreter
import com.gmvalentino.main.contract.MainAction
import com.gmvalentino.main.contract.MainIntent

class MainInterpreter : Interpreter<MainIntent, MainAction> {
    override suspend fun interpret(intent: MainIntent): MainAction = when (intent) {
        is MainIntent.TaskClicked -> MainAction.Toggle(intent.id)
        is MainIntent.CreateClicked -> MainAction.Create(intent.title, intent.dueDate)
        is MainIntent.DeleteClicked -> MainAction.Remove(intent.id)
        MainIntent.ResolveErrorClicked -> MainAction.ResolveError
    }
}
