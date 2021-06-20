package com.gmvalentino.second

import com.gmvalentino.Intent

sealed class SecondIntent : Intent {
    object Increment : SecondIntent()
    object Decrement : SecondIntent()
}
