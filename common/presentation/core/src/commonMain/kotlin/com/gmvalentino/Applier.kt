package com.gmvalentino

class Applier<STATE: State, ACTION: Action>(
    vararg val middleware: Middleware<STATE, ACTION>
)
