package com.gmvalentino

import com.gmvalentino.usecases.GetTasksUseCase
import com.gmvalentino.usecases.GetTasksUseCaseProtocol
import com.gmvalentino.usecases.UpdateTaskUseCase
import com.gmvalentino.usecases.UpdateTaskUseCaseProtocol
import org.koin.dsl.module

val domainModule = module {
    factory<GetTasksUseCaseProtocol> {
        GetTasksUseCase(
            get()
        )
    }

    factory<UpdateTaskUseCaseProtocol> {
        UpdateTaskUseCase(
            get()
        )
    }
}
