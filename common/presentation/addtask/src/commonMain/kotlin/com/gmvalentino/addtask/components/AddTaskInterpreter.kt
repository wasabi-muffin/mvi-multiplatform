package com.gmvalentino.addtask.components

import com.gmvalentino.addtask.contract.AddTaskAction
import com.gmvalentino.addtask.contract.AddTaskIntent
import com.gmvalentino.components.Interpreter

class AddTaskInterpreter : Interpreter<AddTaskIntent, AddTaskAction> {
    override suspend fun interpret(intent: AddTaskIntent): AddTaskAction = when (intent) {
        is AddTaskIntent.InputTitle -> AddTaskAction.ValidateTitle(intent.text)
        is AddTaskIntent.InputDate -> AddTaskAction.FormatAndValidateDate(intent.date)
        AddTaskIntent.CreateClicked -> AddTaskAction.CreateTask
    }
}