package com.gmvalentino

import com.gmvalentino.local.TaskLocalDataSource
import org.koin.dsl.module

val databaseModule = module {
    sqliteDriverModule

    single<TaskLocalDataSource> {
        TaskDao(
            get()
        )
    }
}
