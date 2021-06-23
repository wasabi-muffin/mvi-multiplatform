package com.gmvalentino.entities

import kotlinx.datetime.LocalDateTime

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val date: LocalDateTime,
    val isComplete: Boolean
)