package com.gmvalentino.main

import com.gmvalentino.Action

sealed class MainAction : Action {
    data class Add(val value: Int) : MainAction()
    object LoadCounter : MainAction()
}
