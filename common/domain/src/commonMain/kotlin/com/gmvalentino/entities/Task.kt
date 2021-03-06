package com.gmvalentino.entities

import kotlinx.datetime.LocalDate

data class Task(
    val id: String,
    val title: String,
    val dueDate: LocalDate,
    val isComplete: Boolean
)