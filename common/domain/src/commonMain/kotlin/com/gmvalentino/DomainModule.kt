package com.gmvalentino

import com.gmvalentino.usecases.*
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

    factory<ValidateTaskTitleUseCase> {
        ValidateTaskTitle()
    }

    factory<ValidateTaskDateUseCase> {
        ValidateTaskDate()
    }

    factory<FormatAndValidateTaskDateUseCase> {
        FormatAndValidateTaskDate(
            get(),
            get()
        )
    }
}
