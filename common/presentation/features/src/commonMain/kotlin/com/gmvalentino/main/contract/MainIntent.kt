package com.gmvalentino.main.contract

import com.gmvalentino.contract.Intent
import kotlinx.datetime.LocalDate

sealed class MainIntent : Intent {
    data class CreateClicked(val title: String, val dueDate: LocalDate) : MainIntent()
    data class TaskClicked(val id: String) : MainIntent()
    data class DeleteClicked(val id: String) : MainIntent()
    object ResolveErrorClicked : MainIntent()
}
