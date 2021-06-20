package com.gmvalentino

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val databaseModule = module {
    sqliteDriverModule

    single<CounterDao> {
        CounterDao(
            get(),
            Dispatchers.Default
        )
    }
}
