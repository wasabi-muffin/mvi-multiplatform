package com.gmvalentino.overview

import com.gmvalentino.overview.components.OverviewInterpreter
import com.gmvalentino.overview.components.OverviewProcessor
import com.gmvalentino.overview.components.OverviewReducer
import com.gmvalentino.overview.components.OverviewStore
import org.koin.dsl.module

val overviewModule = module {

    single {
        OverviewInterpreter()
    }

    single {
        OverviewProcessor(
            get(),
            get(),
            get()
        )
    }

    single {
        OverviewReducer()
    }

    factory {
        OverviewStore(
            interpreter = get(),
            processor = get(),
            reducer = get()
        )
    }
}
