package com.ayogeshwaran.githubclient.network

/**
 * This class is a generic sealed class with the possible result types.
 */
sealed class NetworkResult<T>(
    val data: T? = null,
    val message: Any? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data)

    class Error<T>(errorCode: ErrorCode, data: T? = null) : NetworkResult<T>(data, errorCode)

    class Loading<T> : NetworkResult<T>()
}