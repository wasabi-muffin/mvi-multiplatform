package com.gmvalentino

import com.gmvalentino.usecases.CreateTask
import com.gmvalentino.usecases.CreateTaskUseCase
import com.gmvalentino.usecases.GetTasks
import com.gmvalentino.usecases.GetTasksUseCase
import com.gmvalentino.usecases.RemoveTask
import com.gmvalentino.usecases.RemoveTaskUseCase
import com.gmvalentino.usecases.UpdateTask
import com.gmvalentino.usecases.UpdateTaskUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetTasksUseCase> {
        GetTasks(
            get()
        )
    }

    factory<UpdateTaskUseCase> {
        UpdateTask(
            get()
        )
    }

    factory<RemoveTaskUseCase> {
        RemoveTask(
            get()
        )
    }

    factory<CreateTaskUseCase> {
        CreateTask(
            get()
        )
    }
}
