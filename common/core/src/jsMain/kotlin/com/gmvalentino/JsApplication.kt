package com.gmvalentino

import co.touchlab.kermit.ConsoleLogger
import co.touchlab.kermit.Kermit
import org.koin.dsl.module

actual object MultiplatformApplication {

    fun initialize() {
        initApplication(
            module {
                val baseKermit = Kermit(ConsoleLogger())
                factory { (tag: String?) -> if (tag != null) baseKermit.withTag(tag) else baseKermit }
            }
        )
    }
}