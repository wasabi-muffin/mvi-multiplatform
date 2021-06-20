package com.gmvalentino

import com.gmvalentino.repositories.CounterRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {

    single<CounterRepository> {
        CounterDataRepository(
            get()
        )
    }
}
