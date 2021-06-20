package com.gmvalentino

import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import org.koin.dsl.module

actual val settingsModule = module {
    single<Settings> { AndroidSettings(get()) }
}
