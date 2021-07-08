package com.gmvalentino.android

import android.app.Application
import com.gmvalentino.MultiplatformApplication
import com.gmvalentino.StoreInjector
import com.gmvalentino.android.task.OverviewViewModel
import com.gmvalentino.getStore
import com.gmvalentino.overview.components.OverviewStore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiplatformApplication.initialize(
            applicationContext,
            applicationContext.getSharedPreferences("Default", MODE_PRIVATE)
        )

        startKoin {
            modules(
                module {
                    viewModel {
                        OverviewViewModel(
                            StoreInjector { getStore<OverviewStore>() }.store()
                        )
                    }
                }
            )
        }
    }
}
