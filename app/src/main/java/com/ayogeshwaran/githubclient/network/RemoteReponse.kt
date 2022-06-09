package com.ayogeshwaran.githubclient.network

data class RemoteResponse<M>(
    val data: M?,
    val message: String?
)