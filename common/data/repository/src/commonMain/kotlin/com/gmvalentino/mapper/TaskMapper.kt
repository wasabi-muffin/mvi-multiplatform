package com.gmvalentino.mapper

import com.gmvalentino.entities.Task
import com.gmvalentino.models.TaskModel
import com.gmvalentino.models.TodoModel

object TaskMapper {

    fun modelToEntity(model: TaskModel) = Task(
        model.id,
        model.title,
        model.dueDate,
        model.isComplete
    )


    fun entityToModel(entity: Task) = TaskModel(
        entity.id,
        entity.title,
        entity.dueDate,
        entity.isComplete
    )
}