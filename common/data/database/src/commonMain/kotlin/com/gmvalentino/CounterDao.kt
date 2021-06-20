package com.gmvalentino

import com.gmvalentino.db.Counter
import com.gmvalentino.db.Db
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class CounterDao(
    sqlDriver: SqlDriver,
    private val backgroundDispatcher: CoroutineDispatcher
) {
    private val dbRef: Db = Db(sqlDriver)

    fun deleteAll() = dbRef.databaseQueries.deleteAll()

    fun selectAllItems(): Flow<Long> = dbRef.databaseQueries
        .getCounter()
        .asFlow()
        .map {
            it.executeAsOneOrNull()
        }
        .map {
            it?.value ?: 0L
        }
        .flowOn(backgroundDispatcher)

    suspend fun updateCounter(value: Long) {
        dbRef.transactionWithContext(Dispatchers.Default) {
            val counter = dbRef.databaseQueries.getCounter()
                .executeAsOneOrNull()
                ?.value ?: 0
            dbRef.databaseQueries.updateCounter(Counter(0, counter + value))
        }
    }
}
