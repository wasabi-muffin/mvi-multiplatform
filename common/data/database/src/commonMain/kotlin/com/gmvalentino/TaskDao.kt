package com.gmvalentino

import com.gmvalentino.db.Db
import com.gmvalentino.db.TasksDb
import com.gmvalentino.local.TaskLocalDataSource
import com.gmvalentino.models.TaskModel
import com.gmvalentino.models.TodoModel
import com.gmvalentino.models.responses.GetTaskResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single
import kotlinx.datetime.LocalDate
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
                    id = task.id,
                    title = task.title,
                    dueDate = LocalDate.parse(task.date),
                    isComplete = task.is_complete
                )
            }

    override suspend fun saveTasks(tasks: List<TaskModel>) = database.single()
        .tasksQueries.run {
            transactionWithContext(Dispatchers.Default) {
                deleteAll()
                tasks.forEach { task ->
                    addTask(
                        TasksDb(
                            id = task.id,
                            title = task.title,
                            date = task.dueDate.toString(),
                            is_complete = task.isComplete
                        )
                    )
                }
            }
        }

    override suspend fun addTask(task: TaskModel) =
        database.single()
            .tasksQueries.addTask(
                TasksDb(
                    id = task.id,
                    title = task.title,
                    date = task.dueDate.toString(),
                    is_complete = task.isComplete
                )
            )

    override suspend fun removeTask(id: String) =
        database.single().tasksQueries.deleteTask(id)

    override suspend fun updateTask(task: TaskModel) =
        database.single().tasksQueries.updateTask(
            TasksDb(
                id = task.id,
                title = task.title,
                date = task.dueDate.toString(),
                is_complete = task.isComplete
            )
        )
}