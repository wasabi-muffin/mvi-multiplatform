package com.gmvalentino.main.di

import com.gmvalentino.main.components.MainInterpreter
import com.gmvalentino.main.components.MainProcessor
import com.gmvalentino.main.components.MainReducer
import com.gmvalentino.main.components.MainStore
import org.koin.dsl.module

val mainModule = module {

    single {
        MainInterpreter()
    }

    single {
        MainProcessor(
            get(),
            get(),
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
