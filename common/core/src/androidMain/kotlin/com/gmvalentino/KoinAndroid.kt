package com.gmvalentino

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    val baseKermit = Kermit(LogcatLogger()).withTag("Multiplatform")
    factory { (tag: String?) -> if (tag != null) baseKermit.withTag(tag) else baseKermit }
}
