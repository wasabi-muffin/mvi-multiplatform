package com.gmvalentino

import com.gmvalentino.db.Db
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sqliteDriverModule: Module = module {
    single<SqlDriver> {
        NativeSqliteDriver(
            Db.Schema,
            "Db"
        )
    }
}

