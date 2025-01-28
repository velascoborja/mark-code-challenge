package com.medtronic.surgery.app.utils.network

sealed class NetworkResponse<out T : Any> {

    data class Success<T : Any>(val body: T) : NetworkResponse<T>()
    data class Error(val error: Throwable) : NetworkResponse<Nothing>()

    inline fun <P : Any> map(block: (T) -> P): NetworkResponse<P> {
        return when (this) {
            is Success -> Success(block(this.body))
            is Error -> this
        }
    }

    inline fun <P : Any> flatMap(block: (T) -> NetworkResponse<P>): NetworkResponse<P> {
        return when (this) {
            is Success -> block(this.body)
            is Error -> this
        }
    }

    inline fun doOnSuccess(block: (T) -> Unit): NetworkResponse<T> {
        when (this) {
            is Success -> {
                block.invoke(this.body)
            }

            else -> {
                // do nothing
            }
        }
        return this
    }

    inline fun doOnError(block: (Throwable) -> Unit): NetworkResponse<T> {
        when (this) {
            is Error -> {
                block.invoke(this.error)
            }

            else -> {
                // do nothing
            }
        }
        return this
    }

    fun value(): T? {
        return when (this) {
            is Success -> this.body
            is Error -> null
        }
    }
}