package com.gmvalentino.addtask

import com.gmvalentino.addtask.components.AddTaskInterpreter
import com.gmvalentino.addtask.components.AddTaskProcessor
import com.gmvalentino.addtask.components.AddTaskReducer
import com.gmvalentino.addtask.components.AddTaskStore
import org.koin.dsl.module

val addTaskModule = module {

    single {
        AddTaskInterpreter()
    }

    single {
        AddTaskProcessor(
            get(),
            get(),
            get()
        )
    }

    single {
        AddTaskReducer()
    }

    factory {
        AddTaskStore(
            interpreter = get(),
            processor = get(),
            reducer = get()
        )
    }
}
