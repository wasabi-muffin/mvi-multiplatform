package com.gmvalentino.requests

import com.gmvalentino.models.UsersResponse

interface Api {
    fun getUserRequest(): UsersResponse
}
