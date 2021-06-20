package com.gmvalentino.second

import com.gmvalentino.State
import com.gmvalentino.entities.UserEntity

data class SecondState(
    val isLoading: Boolean = false,
    val counter: Long = 0,
) : State
