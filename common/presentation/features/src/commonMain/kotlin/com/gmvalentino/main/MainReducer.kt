package com.gmvalentino.main

import com.gmvalentino.Reducer

class MainReducer : Reducer<MainState, MainResult> {
    override suspend fun reduce(state: MainState, result: MainResult): MainState {
        return when (result) {
            is MainResult.Counter -> {
                state.copy(
                    counter = result.value
                )
            }
            is MainResult.Loading -> {
                state.copy(isLoading = true)
            }
        }
    }
}
