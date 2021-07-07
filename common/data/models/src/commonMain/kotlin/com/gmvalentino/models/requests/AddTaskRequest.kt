package com.gmvalentino.models.requests

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
class AddTaskRequest(
    val title: String,
    val dueDate: LocalDate
)