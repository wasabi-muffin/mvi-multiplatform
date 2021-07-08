package com.gmvalentino

import com.gmvalentino.main.di.mainModule
import com.gmvalentino.overview.overviewModule
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
            coreModule,
            domainModule,
            repositoryModule,
            apiModule,
            databaseModule,
            sqliteDriverModule,
            settingsModule,
            mainModule,
            overviewModule
        )
    )
}

private val coreModule = module {
    single<Clock> {
        Clock.System
    }
}