package org.marioonetti.firebasefundamentals.domain.core

sealed class AppError : Exception() {
    data class Local(
        override val message: String,
    ) : AppError()

    data class Remote(
        override val message: String,
    ) : AppError()

    data object Unknown : AppError()
}
