package com.gmvalentino

import com.gmvalentino.local.TaskLocalDataSource
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val databaseModule = module {
    sqliteDriverModule

    single<TaskLocalDataSource> {
        TaskDao(
            get()
        )
    }
}
