package com.gmvalentino.main

import com.gmvalentino.contract.Event

sealed class MainEvent : Event {
    object Error : MainEvent()
}