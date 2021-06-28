package com.gmvalentino

import com.gmvalentino.db.Db
import com.gmvalentino.db.Tasks
import com.gmvalentino.local.TaskLocalDataSource
import com.gmvalentino.models.TaskModel
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime

class TaskDao(
    sqlDriver: SqlDriver
) : TaskLocalDataSource {
    private val dbRef: Db = Db(sqlDriver)

    override suspend fun getTasks(): Flow<List<TaskModel>> = dbRef
        .tasksQueries
        .getTasks()
        .asFlow()
        .map {
            it.executeAsList().map { task ->
                TaskModel(
                    task.id,
                    task.title,
                    task.description,
                    LocalDateTime.parse(task.date),
                    task.is_complete
                )
            }
        }
        .flowOn(Dispatchers.Default)

    override suspend fun addTask(task: TaskModel) =
        dbRef.transactionWithContext(Dispatchers.Main) {
            dbRef.tasksQueries
                .addTask(
                    Tasks(
                        task.id,
                        task.title,
                        task.details,
                        task.date.toString(),
                        task.isComplete
                    )
                )
        }

    override suspend fun removeTask(id: String) =
        dbRef.transactionWithContext(Dispatchers.Main) {
            dbRef.tasksQueries
                .deleteTask(id)
        }

    override suspend fun updateTask(id: String, isComplete: Boolean) =
        dbRef.transactionWithContext(Dispatchers.Main) {
            dbRef.tasksQueries
                .updateTask(isComplete, id)
        }
}