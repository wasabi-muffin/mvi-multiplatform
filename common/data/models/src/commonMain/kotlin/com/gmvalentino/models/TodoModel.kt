package com.gmvalentino.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskModel(
    val id: String,
    val title: String,
    val description: String,
    val date: LocalDateTime,
    @SerialName("is_complete") val isComplete: Boolean
)