package com.gmvalentino.repositories

import com.gmvalentino.entities.Error

interface ErrorRepository {

    fun getError(throwable: Throwable): Error
}
