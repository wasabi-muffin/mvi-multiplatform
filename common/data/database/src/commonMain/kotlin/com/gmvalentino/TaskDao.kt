package com.gmvalentino

import com.gmvalentino.db.Db
import com.gmvalentino.db.Tasks
import com.gmvalentino.local.TaskLocalDataSource
import com.gmvalentino.models.TaskModel
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.datetime.LocalDateTime

class TaskDao(
    sqlDriver: Flow<SqlDriver>
) : TaskLocalDataSource {
    private val dbRef = sqlDriver.map {
        Db(it).tasksQueries
    }

    override suspend fun getTasks(): Flow<List<TaskModel>> = dbRef
        .map { it.getTasks() }
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
        dbRef.flowOn(Dispatchers.Main)
            .map {
                it.addTask(
                    Tasks(
                        task.id,
                        task.title,
                        task.details,
                        task.date.toString(),
                        task.isComplete
                    )
                )
            }
            .single()

    override suspend fun removeTask(id: String) =
        dbRef.flowOn(Dispatchers.Main)
            .map {
                it.deleteTask(id)
            }
            .single()

    override suspend fun updateTask(id: String, isComplete: Boolean) =
        dbRef.flowOn(Dispatchers.Main)
            .map {
                it.updateTask(isComplete, id)
            }
            .single()
}