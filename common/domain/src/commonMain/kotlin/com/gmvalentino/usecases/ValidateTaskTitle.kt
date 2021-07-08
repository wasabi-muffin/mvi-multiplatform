package com.gmvalentino.usecases

class ValidateTaskTitle : ValidateTaskTitleUseCase {
    override suspend fun execute(arguments: ValidateTaskTitleUseCase.Args): Boolean {
        return arguments.text.length in 1..40
    }
}

interface ValidateTaskTitleUseCase : UseCase<Boolean, ValidateTaskTitleUseCase.Args> {
    data class Args(
        val text: String
    )
}