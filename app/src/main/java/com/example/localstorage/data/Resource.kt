package com.example.localstorage.data

sealed class Resource<out T> {

    class Success<out T>(val value: T) : Resource<T>()

    class Failure(
        val failureStatus: FailureStatus,
        val code: Int? = null,
        val message: String? = null
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()

    object Default : Resource<Nothing>()
}


enum class FailureStatus {
    EMPTY,
    API_FAIL,
    NO_INTERNET,
    ACCESS_DENIED,
    USER_UNAPPROVED,
    OTHER
}