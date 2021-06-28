package com.gmvalentino

import com.gmvalentino.main.mainModule
import kotlinx.datetime.Clock
import org.koin.core.module.Module
import org.koin.dsl.koinApplication
import org.koin.dsl.module

expect object MultiplatformApplication

val koinApplication = koinApplication()

fun initApplication(appModule: Module) {
    koinApplication.koin.loadModules(
        listOf(
            appModule,
            platformModule,
            coreModule,
            domainModule,
            repositoryModule,
            apiModule,
            databaseModule,
            sqliteDriverModule,
            settingsModule,
            mainModule
        )
    )
}

private val coreModule = module {
    single<Clock> {
        Clock.System
    }
}