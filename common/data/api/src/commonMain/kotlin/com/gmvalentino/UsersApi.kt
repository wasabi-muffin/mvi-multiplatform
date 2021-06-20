package com.gmvalentino

import com.gmvalentino.models.UsersResponse

interface UsersApi {
    suspend fun getUsers(): UsersResponse
}
