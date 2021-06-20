package com.gmvalentino

interface Middleware<STATE: State, ACTION: Action> {
    fun apply(state: STATE, action: ACTION) : ACTION
}
