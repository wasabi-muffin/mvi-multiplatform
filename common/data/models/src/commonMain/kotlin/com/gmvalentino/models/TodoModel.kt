package com.gmvalentino.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoModel(
    val id: String,
    val title: String,
    @SerialName("description") val details: String,
    val date: LocalDateTime,
    @SerialName("is_complete") val isComplete: Boolean
)