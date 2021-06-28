package com.gmvalentino.android

import android.app.Application
import com.gmvalentino.MultiplatformApplication
import com.gmvalentino.StoreInjector
import com.gmvalentino.android.task.TaskViewModel
import com.gmvalentino.getStore
import com.gmvalentino.main.MainStore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiplatformApplication.initialize(
            applicationContext,
            applicationContext.getSharedPreferences("KAMPSTARTER_SETTINGS", MODE_PRIVATE)
        )

        startKoin {
            modules(
                module {
                    viewModel {
                        TaskViewModel(
                            StoreInjector { getStore<MainStore>() }.store()
                        )
                    }
                }
            )
        }
    }
}
