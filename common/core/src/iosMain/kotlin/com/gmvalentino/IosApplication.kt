package com.gmvalentino

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.NSLogLogger
import org.koin.dsl.module

actual object MultiplatformApplication {

    fun initialize() {
        initApplication(
            module {
                val baseKermit = Kermit(NSLogLogger())
                factory { (tag: String?) -> if (tag != null) baseKermit.withTag(tag) else baseKermit }
            }
        )
    }
}