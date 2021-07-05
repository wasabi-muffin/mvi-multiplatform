package com.gmvalentino.repository

import co.touchlab.kermit.Kermit
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
        runCatching {
            remoteDataSource.getTasks()
        }
            .onSuccess {
                emit(it.map(TaskMapper::modelToEntity))
                localDataSource.saveTasks(it)
            }
            .onFailure {
                Kermit().d { "Error $it" }
            }
        emitAll(localDataSource.getTasks().map { it.map(TaskMapper::modelToEntity) })
    }

    override suspend fun addTask(task: Task) {
        runCatching {
            remoteDataSource.addTask(
                TaskMapper.entityToModel(task)
            )
        }.onSuccess {
            localDataSource.addTask(
                TaskMapper.entityToModel(task)
            )
        }
            .onFailure {
                throw it
            }
    }

    override suspend fun deleteTask(id: String) {
        runCatching {
            remoteDataSource.removeTask(id)
        }
            .onSuccess {
                localDataSource.removeTask(id)
            }
            .onFailure {
                throw it
            }
    }

    override suspend fun updateTask(id: String, isComplete: Boolean) {
        runCatching {
            remoteDataSource.updateTask(id, isComplete)
        }
            .onSuccess {
                localDataSource.updateTask(id, isComplete)
            }
            .onFailure {
                throw it
            }
    }
}