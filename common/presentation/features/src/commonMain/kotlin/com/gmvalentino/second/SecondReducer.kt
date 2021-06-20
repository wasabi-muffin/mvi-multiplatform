package com.gmvalentino.second

import com.gmvalentino.Reducer

class SecondReducer : Reducer<SecondState, SecondResult> {
    override suspend fun reduce(state: SecondState, result: SecondResult): SecondState {
        return when (result) {
            is SecondResult.Counter -> {
                state.copy(
                    isLoading = false,
                    counter = result.value
                )
            }
            is SecondResult.Loading -> {
                state.copy(isLoading = true)
            }
        }
    }
}
