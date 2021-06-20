package com.gmvalentino

import com.gmvalentino.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.gmvalentino.repositories.UsersRepository

class UsersDataRepository(
    private val usersApi: UsersApi
) : UsersRepository {

    override suspend fun getUsers(): List<UserEntity> = withContext(Dispatchers.Main) {
        usersApi.getUsers().results.map(UsersMapper::toEntity)
    }
}
