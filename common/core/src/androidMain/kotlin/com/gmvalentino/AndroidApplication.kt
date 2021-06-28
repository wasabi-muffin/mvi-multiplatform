package com.gmvalentino

import android.content.Context
import android.content.SharedPreferences
import org.koin.dsl.module

actual object MultiplatformApplication {

    fun initialize(
        context: Context,
        preferences: SharedPreferences
    ) {
        initApplication(
            module {
                single { context }
                single { preferences }
            }
        )
    }
}