package com.gmvalentino.main

import com.gmvalentino.State
import com.gmvalentino.entities.UserEntity

data class MainState(
    val isLoading: Boolean = false,
    val counter: Long = 0,
) : State
