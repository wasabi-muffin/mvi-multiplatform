package com.gmvalentino

import android.content.Context
import android.content.SharedPreferences
import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
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
                val baseKermit = Kermit(LogcatLogger()).withTag("Multiplatform")
                factory { (tag: String?) -> if (tag != null) baseKermit.withTag(tag) else baseKermit }
            }
        )
    }
}