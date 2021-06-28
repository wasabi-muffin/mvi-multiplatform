package com.gmvalentino

interface Reducer<STATE : State, in RESULT : Result> {
    suspend fun reduce(state: STATE, result: RESULT): STATE
}
