package com.gmvalentino.usecases

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

interface UseCase<out Type, in Arguments> {

    suspend fun execute(arguments: Arguments): Type

    abstract class Arguments

    object None : Arguments()
}
