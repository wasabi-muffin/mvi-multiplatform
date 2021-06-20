package com.gmvalentino.second

import com.gmvalentino.Action

sealed class SecondAction : Action {
    data class Add(val value: Int) : SecondAction()
    object LoadCounter : SecondAction()
}
