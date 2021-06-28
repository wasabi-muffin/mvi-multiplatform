package com.gmvalentino.mapper

import com.gmvalentino.entities.Task
import com.gmvalentino.models.TaskModel

object TaskMapper {

    fun modelToEntity(model: TaskModel) = Task(
        model.id,
        model.title,
        model.details,
        model.date,
        model.isComplete
    )

    fun entityToModel(entity: Task) = TaskModel(
        entity.id,
        entity.title,
        entity.details,
        entity.date,
        entity.isComplete
    )
}