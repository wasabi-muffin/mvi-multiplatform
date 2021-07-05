package com.gmvalentino

import com.gmvalentino.db.Db
import com.squareup.sqldelight.drivers.sqljs.initSqlDriver
import kotlinx.coroutines.await
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sqliteDriverModule: Module = module {
    single<suspend () -> Db> {
        suspend { Db(initSqlDriver(Db.Schema).await()) }
    }
}
