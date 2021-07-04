package com.gmvalentino

import com.gmvalentino.db.Db
import com.squareup.sqldelight.android.AndroidSqliteDriver
import kotlinx.coroutines.flow.flowOf
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sqliteDriverModule: Module = module {
    single {
        flowOf(
            AndroidSqliteDriver(
                Db.Schema,
                get(),
                "Db"
            )
        )
    }
}
