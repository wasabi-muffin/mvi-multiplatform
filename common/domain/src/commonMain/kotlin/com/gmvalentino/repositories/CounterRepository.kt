package com.gmvalentino.repositories

import com.gmvalentino.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface CounterRepository {

    suspend fun getCounter(): Flow<Long>

    suspend fun setCounter(value: Long)
}
