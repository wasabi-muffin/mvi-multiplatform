package com.gmvalentino.main

import com.gmvalentino.Intent

sealed class MainIntent : Intent {
    data class Toggle(val id: String): MainIntent()
}
