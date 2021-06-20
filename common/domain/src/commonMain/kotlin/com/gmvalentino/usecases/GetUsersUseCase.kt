package com.gmvalentino.usecases

import com.gmvalentino.entities.UserEntity
import com.gmvalentino.repositories.UsersRepository

class GetUsersUseCase(
    private val usersRepository: UsersRepository
) : GetUsersUseCaseProtocol() {
    override suspend fun task(arguments: None): List<UserEntity> = usersRepository.getUsers()
}

abstract class GetUsersUseCaseProtocol : UseCase<List<UserEntity>, UseCase.None>()
