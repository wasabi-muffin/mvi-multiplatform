package com.gmvalentino

import co.touchlab.kermit.Kermit
import com.gmvalentino.db.Db
import com.gmvalentino.db.Tasks
import com.gmvalentino.local.TaskLocalDataSource
import com.gmvalentino.models.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single
import kotlinx.datetime.LocalDateTime

@FlowPreview
class TaskDao(
    private val database: Flow<Db>,
) : TaskLocalDataSource {

    override suspend fun getTasks(): List<TaskModel> =
        database.single()
            .tasksQueries
            .getTasks()
            .executeAsList()
            .map { task ->
                TaskModel(
                    task.id,
                    task.title,
                    task.description,
                    LocalDateTime.parse(task.date),
                    task.is_complete
                )
            }

    override suspend fun saveTasks(tasks: List<TaskModel>) = database.single()
        .tasksQueries.run {
            transactionWithContext(Dispatchers.Default) {
                deleteAll()
                tasks.forEach { task ->
                    addTask(
                        Tasks(
                            id = task.id,
                            title = task.title,
                            description = task.details,
                            date = task.date.toString(),
                            is_complete = task.isComplete
                        )
                    )
                }
            }
        }

    override suspend fun addTask(task: TaskModel) =
        database.single()
            .tasksQueries.addTask(
                Tasks(
                    id = task.id,
                    title = task.title,
                    description = task.details,
                    date = task.date.toString(),
                    is_complete = task.isComplete
                )
            )

    override suspend fun removeTask(id: String) =
        database.single().tasksQueries.deleteTask(id)

    override suspend fun updateTask(id: String, isComplete: Boolean) =
        database.single().tasksQueries.updateTask(isComplete, id)
}