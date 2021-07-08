package com.gmvalentino.addtask.contract

import com.gmvalentino.contract.Intent

sealed class AddTaskIntent : Intent {
    data class InputTitle(val text: String) : AddTaskIntent()
    data class InputDate(val date: String) : AddTaskIntent()
    object CreateClicked : AddTaskIntent()
}