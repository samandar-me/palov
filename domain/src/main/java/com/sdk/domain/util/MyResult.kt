package com.sdk.domain.util

sealed class MyResult<out T> {
    object Loading: MyResult<Nothing>()
    data class Error(val message: String): MyResult<Nothing>()
    data class Success<out T>(val data: T): MyResult<T>()
}
