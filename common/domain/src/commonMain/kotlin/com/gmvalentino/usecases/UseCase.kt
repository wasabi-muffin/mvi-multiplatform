package com.gmvalentino.usecases

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class UseCase<out Type, in Arguments> :
    CoroutineScope where Type : Any, Arguments : UseCase.Arguments {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    internal abstract suspend fun task(arguments: Arguments): Type

    suspend fun execute(arguments: Arguments): Type = withContext(coroutineContext) {
        runCatching {
            task(arguments)
        }.fold(
            onSuccess = {
                it
            },
            onFailure = {
                throw it
            }
        )
    }

    abstract class Arguments

    object None : Arguments()
}
