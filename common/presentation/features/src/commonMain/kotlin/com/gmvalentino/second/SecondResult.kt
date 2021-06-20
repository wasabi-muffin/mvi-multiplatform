package com.gmvalentino.second

import com.gmvalentino.Result

sealed class SecondResult : Result {
    object Loading : SecondResult()
    data class Counter(val value: Long) : SecondResult()
}
