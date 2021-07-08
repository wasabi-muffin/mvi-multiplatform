package com.gmvalentino.components

import com.gmvalentino.contract.Result
import com.gmvalentino.contract.State

interface Reducer<in RESULT : Result, STATE : State> {
    suspend fun reduce(result: RESULT, state: STATE): STATE
}