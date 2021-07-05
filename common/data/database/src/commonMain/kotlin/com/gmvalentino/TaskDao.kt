package com.gmvalentino

import co.touchlab.kermit.Kermit
import com.gmvalentino.db.Db
import com.gmvalentino.db.Tasks
import com.gmvalentino.local.TaskLocalDataSource
import com.gmvalentino.models.TaskModel
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime

@FlowPreview
class TaskDao(
    private val dbRef: suspend () -> Db,
) : TaskLocalDataSource {

    val log = Kermit(defaultTag = "SQL DEBUG")

    override suspend fun getTasks(): Flow<List<TaskModel>> = dbRef()
        .tasksQueries
        .getTasks()
        .asFlow()
        .mapToList()
        .map {
            it.map { task ->
                TaskModel(
                    task.id,
                    task.title,
                    task.description,
                    LocalDateTime.parse(task.date),
                    task.is_complete
                )
            }
        }

    override suspend fun saveTasks(tasks: List<TaskModel>) = dbRef().tasksQueries.run {
        transactionWithContext(Dispatchers.Default) {
            log.d { "Start Save Tasks" }
            deleteAll()
            log.d { "Delete All" }
            tasks.forEach { task ->
                log.d { "Add task $task" }
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
        dbRef().tasksQueries.addTask(
            Tasks(
                id = task.id,
                title = task.title,
                description = task.details,
                date = task.date.toString(),
                is_complete = task.isComplete
            )
        )

    override suspend fun removeTask(id: String) =
        dbRef().tasksQueries.deleteTask(id)

    override suspend fun updateTask(id: String, isComplete: Boolean) =
        dbRef().tasksQueries.updateTask(isComplete, id)
}