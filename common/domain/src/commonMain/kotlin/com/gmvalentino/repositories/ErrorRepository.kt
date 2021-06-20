package com.gmvalentino.repositories

import com.gmvalentino.entities.ErrorEntity

interface ErrorRepository {

    fun getError(throwable: Throwable): ErrorEntity
}
