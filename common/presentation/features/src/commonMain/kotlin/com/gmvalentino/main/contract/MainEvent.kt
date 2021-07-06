package com.gmvalentino.main.contract

import com.gmvalentino.contract.Event

sealed class MainEvent : Event {
    object Error : MainEvent()
}