package com.gmvalentino

class Loader<out ACTION: Action>(
    vararg val actions: ACTION
)
