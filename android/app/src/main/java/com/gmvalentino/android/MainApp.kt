package com.gmvalentino.android

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.gmvalentino.android.task.TaskViewModel
import com.gmvalentino.initKoin
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(
            module {
                viewModel { TaskViewModel(get()) }
                single<Context> { this@MainApp }
                single<SharedPreferences> {
                    get<Context>().getSharedPreferences("KAMPSTARTER_SETTINGS", MODE_PRIVATE)
                }
                single {
                    { Log.i("Startup", "Hello from Android/Kotlin!") }
                }
            }
        )
    }
}
