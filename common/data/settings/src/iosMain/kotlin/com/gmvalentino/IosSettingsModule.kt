package com.gmvalentino

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.Settings
import org.koin.dsl.module

actual val settingsModule = module {
    single<Settings> { AppleSettings(get()) }
}
