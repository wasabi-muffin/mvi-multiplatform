package com.gmvalentino

import com.gmvalentino.db.Db
import com.squareup.sqldelight.android.AndroidSqliteDriver
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sqliteDriverModule: Module = module {
    single<suspend () -> Db> {
        suspend {
            get()
        }
    }

    single<Db> {
        Db(
            AndroidSqliteDriver(
                Db.Schema,
                get(),
                "Db"
            )
        )
    }
}
