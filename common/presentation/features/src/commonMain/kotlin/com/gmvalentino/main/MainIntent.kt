package com.gmvalentino.main

import com.gmvalentino.contract.Intent
import com.gmvalentino.entities.Task

sealed class MainIntent : Intent {
    data class CreateClicked(val task: Task): MainIntent()
    data class TaskClicked(val id: String): MainIntent()
    data class DeleteClicked(val id: String): MainIntent()
    object ResolveErrorClicked: MainIntent()
}
