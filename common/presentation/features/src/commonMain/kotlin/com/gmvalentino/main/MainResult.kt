package com.gmvalentino.main

import com.gmvalentino.Result

sealed class MainResult : Result {
    object Loading : MainResult()
    data class Counter(val value: Long) : MainResult()
}
