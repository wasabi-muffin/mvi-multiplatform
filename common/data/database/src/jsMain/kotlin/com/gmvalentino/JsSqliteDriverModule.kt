package com.gmvalentino

import com.gmvalentino.db.Db
import com.squareup.sqldelight.drivers.sqljs.initSqlDriver
import kotlinx.coroutines.await
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.module.Module
import org.koin.dsl.module

private object DbWrapper {
    var db: Db? = null
        set(value) {
            if (db == null) { field = value }
        }
}

actual val sqliteDriverModule: Module = module {
    single<Flow<Db>> {
        flow {
            DbWrapper.db = Db(initSqlDriver(Db.Schema).await())
            emit(requireNotNull(DbWrapper.db))
        }
    }
}

