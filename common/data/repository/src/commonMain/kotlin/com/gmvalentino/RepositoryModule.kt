package com.gmvalentino

import com.gmvalentino.repositories.TaskRepository
import com.gmvalentino.repository.TaskDataRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {

    single<TaskRepository> {
        TaskDataRepository(
            get(),
            get()
        )
    }
}
