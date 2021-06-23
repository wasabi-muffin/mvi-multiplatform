package com.gmvalentino.main

import com.gmvalentino.Reducer

class MainReducer : Reducer<MainState, MainResult> {
    override suspend fun reduce(state: MainState, result: MainResult): MainState {
        return when (result) {
            is MainResult.Tasks -> {
                state.copy(
                    tasks = result.data,
                    isLoading = false
                )
            }
            is MainResult.Loading -> {
                state.copy(isLoading = true)
            }
        }
    }
}
