package com.gmvalentino.components

import com.gmvalentino.contract.Result
import com.gmvalentino.contract.State

interface Reducer<STATE : State, in RESULT : Result> {
    suspend fun reduce(state: STATE, result: RESULT): STATE
}