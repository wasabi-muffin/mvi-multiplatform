package com.gmvalentino.models.responses

import com.gmvalentino.models.TaskModel
import kotlinx.serialization.Serializable

@Serializable
data class AddTaskResponse(
    val task: TaskModel
)