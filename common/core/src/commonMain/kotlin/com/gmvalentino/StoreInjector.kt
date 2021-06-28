package com.gmvalentino

import com.gmvalentino.main.MainStore
import org.koin.mp.KoinPlatformTools
import kotlin.native.concurrent.ThreadLocal

inline fun <reified STORE : Store<*, *, *>> getStore() = koinApplication.koin.get<STORE>()

fun interface StoreInjector<STORE : Store<*, *, *>> {
    fun store(): STORE
}

@ThreadLocal
object StoreProvider {
    fun main() = getStore<MainStore>()
}