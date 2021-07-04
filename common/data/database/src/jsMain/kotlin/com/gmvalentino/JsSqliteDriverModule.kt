package com.gmvalentino

import com.gmvalentino.db.Db
import com.squareup.sqldelight.drivers.sqljs.initSqlDriver
import kotlinx.coroutines.await
import kotlinx.coroutines.flow.flow
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sqliteDriverModule: Module = module {
    single {
        flow {
            emit(initSqlDriver(Db.Schema).await())
        }
    }
}
