package com.gmvalentino

import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

actual object MultiplatformApplication {

    fun initialize() {
        initApplication(
            module {
            }
        )
    }
}