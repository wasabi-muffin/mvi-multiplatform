package com.gmvalentino

import com.gmvalentino.db.Db
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sqliteDriverModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            Db.Schema,
            get(),
            "Db"
        )
    }
}
