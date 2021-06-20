package com.gmvalentino

import com.gmvalentino.repositories.CounterRepository
import kotlinx.coroutines.flow.Flow

class CounterDataRepository(
    private val dao: CounterDao
) : CounterRepository {
    override suspend fun getCounter(): Flow<Long> {
        return dao.selectAllItems()
    }

    override suspend fun setCounter(value: Long) {
        dao.updateCounter(value)
    }
}
