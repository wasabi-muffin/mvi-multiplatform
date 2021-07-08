package com.gmvalentino.usecases

import kotlinx.datetime.*

class ValidateTaskDate : ValidateTaskDateUseCase {

    override suspend fun execute(arguments: ValidateTaskDateUseCase.Args): Boolean {
        val date = try {
            LocalDate.parse(arguments.date)
        } catch (e: Exception) {
            return false
        }
        val today = Clock.System.todayAt(TimeZone.currentSystemDefault())
        return date >= today
    }
}

interface ValidateTaskDateUseCase : UseCase<Boolean, ValidateTaskDateUseCase.Args> {
    data class Args(
        val date: String
    )
}