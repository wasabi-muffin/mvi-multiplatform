package com.gmvalentino.repository

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.Logger
import com.gmvalentino.entities.Task
import com.gmvalentino.local.TaskLocalDataSource
import com.gmvalentino.mapper.TaskMapper
import com.gmvalentino.remote.TaskRemoteDataSource
import com.gmvalentino.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class TaskDataRepository(
    private val localDataSource: TaskLocalDataSource,
    private val remoteDataSource: TaskRemoteDataSource
) : TaskRepository {
    override suspend fun getTasks(): Flow<List<Task>> = flow {
        // emitAll(
        //     localDataSource.getTasks().map {
        //         it.map(TaskMapper::modelToEntity)
        //     }
        // )
        val test = remoteDataSource.getTasks().map(TaskMapper::modelToEntity)
        Kermit().d("Test") {
            test.toString()
        }
        emit(test)
    }

    override suspend fun addTask(task: Task) {
        localDataSource.addTask(
            TaskMapper.entityToModel(task)
        )
        remoteDataSource.addTask(
            TaskMapper.entityToModel(task)
        )
    }

    override suspend fun deleteTask(id: String) {
        localDataSource.removeTask(id)
        remoteDataSource.removeTask(id)
    }

    override suspend fun updateTask(id: String, isComplete: Boolean) {
        localDataSource.updateTask(id, isComplete)
        remoteDataSource.updateTask(id, isComplete)
    }
}