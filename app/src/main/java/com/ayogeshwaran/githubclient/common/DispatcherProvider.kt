package com.ayogeshwaran.githubclient.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Dispatcher provider for coroutines.
 */
class DispatcherProvider @Inject constructor() {
    fun main(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    fun default(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    fun io(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    fun unconfined(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }
}
