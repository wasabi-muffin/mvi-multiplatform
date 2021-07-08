package com.gmvalentino.usecases

class FormatTaskDate : FormatTaskDateUseCase {

    /**
     * Not proper formatting code, just a simple implementation
     */
    override suspend fun execute(arguments: FormatTaskDateUseCase.Args): String {
        val digits = arguments.date.filter { it.isDigit() }
        val builder = StringBuilder(digits)
        if (digits.length > 4) {
            builder.insert(4, '-')
        }
        if (digits.length > 6) {
            builder.insert(7, '-')
        }
        if (digits.length > 8) {
            builder.deleteRange(9, builder.lastIndex)
        }
        return builder.toString()
    }
}

interface FormatTaskDateUseCase : UseCase<String, FormatTaskDateUseCase.Args> {
    data class Args(
        val date: String
    )
}