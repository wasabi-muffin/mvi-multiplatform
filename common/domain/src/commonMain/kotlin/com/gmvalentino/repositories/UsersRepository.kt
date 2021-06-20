package com.gmvalentino.repositories

import com.gmvalentino.entities.UserEntity

interface UsersRepository {

    suspend fun getUsers(): List<UserEntity>
}
