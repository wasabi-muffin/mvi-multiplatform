package com.gmvalentino.main

import com.gmvalentino.Intent

sealed class MainIntent : Intent {
    object Increment : MainIntent()
    object Decrement : MainIntent()
}
