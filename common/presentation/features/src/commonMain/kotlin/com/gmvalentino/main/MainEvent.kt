package com.gmvalentino.main

import com.gmvalentino.Event

sealed class MainEvent : Event {
    object Test : MainEvent()
}