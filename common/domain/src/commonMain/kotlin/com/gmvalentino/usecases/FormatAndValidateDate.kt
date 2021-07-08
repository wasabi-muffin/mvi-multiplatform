package com.gmvalentino.usecases

class FormatAndValidateTaskDate(
    private val formatTaskDateUseCase: FormatTaskDateUseCase,
    private val validateTaskDateUseCase: ValidateTaskDateUseCase
) : FormatAndValidateTaskDateUseCase {

    override suspend fun execute(arguments: FormatAndValidateTaskDateUseCase.Args):
            FormatAndValidateTaskDateUseCase.Result {
        val formattedDate = formatTaskDateUseCase.execute(
            FormatTaskDateUseCase.Args(arguments.date)
        )
        val isValidDate = validateTaskDateUseCase.execute(
            ValidateTaskDateUseCase.Args(formattedDate)
        )
        return FormatAndValidateTaskDateUseCase.Result(formattedDate, isValidDate)
    }
}

interface FormatAndValidateTaskDateUseCase :
    UseCase<FormatAndValidateTaskDateUseCase.Result, FormatAndValidateTaskDateUseCase.Args> {
    data class Args(
        val date: String
    )

    data class Result(
        val date: String,
        val isValid: Boolean
    )
}