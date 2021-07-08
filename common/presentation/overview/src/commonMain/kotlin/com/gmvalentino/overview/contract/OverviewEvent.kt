package com.gmvalentino.overview.contract

import com.gmvalentino.contract.Event
import com.gmvalentino.entities.Task

sealed class OverviewEvent : Event {
    object NavigateToCreate : OverviewEvent()
    data class NavigateToEdit(val task: Task) : OverviewEvent()
}