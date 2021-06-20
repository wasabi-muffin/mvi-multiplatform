package com.gmvalentino.second

import org.koin.dsl.module

val secondModule = module {

    single {
        SecondInterpreter()
    }

    single {
        SecondProcessor(
            get(),
            get()
        )
    }

    single {
        SecondReducer()
    }

    factory {
        SecondStore(
            interpreter = get(),
            processor = get(),
            reducer = get()
        )
    }
}
