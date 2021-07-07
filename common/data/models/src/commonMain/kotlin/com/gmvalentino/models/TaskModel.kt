package com.gmvalentino.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class TaskModel(
    val id: String,
    val title: String,
    val dueDate: LocalDate,
    val isComplete: Boolean
)