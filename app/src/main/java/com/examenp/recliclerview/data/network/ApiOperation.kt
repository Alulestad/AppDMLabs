package com.examenp.recliclerview.data.network

sealed interface ApiOperation<T> {
    data class Success<T>(val data: T) : ApiOperation<T> //un data class cuando es corecto
    data class Failure<T>(val exception: Exception) : ApiOperation<T> //un data class cuando es falla

    fun <R> mapSuccess(transform: (T) -> R): ApiOperation<R> { //transform transdorma un T a R
        return when (this) {
            is Success -> Success(transform(data)) //si es siccess
            is Failure -> Failure(exception) //si falla
        }
    }

    fun onSuccess(block: (T) -> Unit): ApiOperation<T> {//toman una función lambda como parámetro y no devuelven nada (Unit)
        if (this is Success) block(data)
        return this
    }

    fun onFailure(block: (Exception) -> Unit): ApiOperation<T> {
        if (this is Failure) block(exception)
        return this
    }
}