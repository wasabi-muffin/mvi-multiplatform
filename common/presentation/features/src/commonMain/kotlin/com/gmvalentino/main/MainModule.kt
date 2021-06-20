package com.gmvalentino.main

import org.koin.dsl.module

val mainModule = module {

    single {
        MainInterpreter()
    }

    single {
        MainProcessor(
            get(),
            get()
        )
    }

    single {
        MainReducer()
    }

    factory {
        MainStore(
            interpreter = get(),
            processor = get(),
            reducer = get()
        )
    }
}
