package com.gmvalentino

import com.gmvalentino.db.Db
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlinx.coroutines.flow.flowOf

actual val sqliteDriverModule: Module = module {
    single {
        flowOf(
            NativeSqliteDriver(
                Db.Schema,
                "Db"
            )
        )
    }
}

