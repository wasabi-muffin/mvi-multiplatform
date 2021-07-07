package com.gmvalentino.models.requests

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class EditTaskRequest(
    val title: String? = null,
    val dueDate: LocalDate? = null,
    val isComplete: Boolean? = null
)