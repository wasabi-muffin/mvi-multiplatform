package com.gmvalentino

import com.gmvalentino.usecases.GetCounterUseCase
import com.gmvalentino.usecases.GetCounterUseCaseProtocol
import com.gmvalentino.usecases.UpdateCounterUseCase
import com.gmvalentino.usecases.UpdateCounterUseCaseProtocol
import org.koin.dsl.module

val domainModule = module {
    factory<GetCounterUseCaseProtocol> {
        GetCounterUseCase(
            get()
        )
    }

    factory<UpdateCounterUseCaseProtocol> {
        UpdateCounterUseCase(
            get()
        )
    }
}
