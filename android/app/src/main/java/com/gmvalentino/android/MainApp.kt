package com.gmvalentino.android

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.gmvalentino.initKoin
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(
            module {

                single<Context> { this@MainApp }
                viewModel { MainViewModel(get()) }
                viewModel { SecondViewModel(get()) }
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
