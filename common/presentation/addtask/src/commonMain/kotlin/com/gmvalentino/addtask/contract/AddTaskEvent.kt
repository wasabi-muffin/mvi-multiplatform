package com.gmvalentino.addtask.contract

import com.gmvalentino.contract.Event
import com.gmvalentino.entities.Task

sealed class AddTaskEvent : Event {
    object Close : AddTaskEvent()
}