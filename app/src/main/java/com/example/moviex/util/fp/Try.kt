package com.example.moviex.util.fp

sealed class Try<V> {

    data class Success<V>(val value: V) : Try<V>()

    data class Failure<V>(val throwable: Throwable) : Try<V>()

    inline fun <NV> map(mapper: (V) -> NV): Try<NV> = when (this) {
        is Success -> from { mapper(value) }
        is Failure -> Failure(throwable)
    }

    inline fun mapThrowable(mapper: (Throwable) -> Throwable): Try<V> = when (this) {
        is Success -> this
        is Failure -> Failure(mapper(throwable))
    }

    inline fun <NV> flatMap(mapper: (V) -> Try<NV>): Try<NV> = when (this) {
        is Success -> mapper(value)
        is Failure -> Failure(throwable)
    }

    inline fun then(ifSuccess: (V) -> Unit): Try<V> = apply {
        if (this is Success) ifSuccess(value)
    }

    inline fun catch(ifFailure: (Throwable) -> Unit): Try<V> = apply {
        if (this is Failure) ifFailure(throwable)
    }

    inline fun finally(block: () -> Unit): Try<V> = apply {
        block()
    }

    companion object {
        inline fun <V> from(block: () -> V): Try<V> = try {
            Success(block())
        } catch (throwable: Throwable) {
            Failure(throwable)
        }
    }
}

fun <V> Try<V>.unwrapOfNull(): V? = when (this) {
    is Try.Success -> value
    is Try.Failure -> null
}