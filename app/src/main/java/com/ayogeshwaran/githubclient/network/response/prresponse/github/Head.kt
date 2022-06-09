package com.ayogeshwaran.githubclient.network.response.prresponse.github

data class Head(
    val label: String,
    val ref: String,
    val repo: RepoX,
    val sha: String,
    val user: UserX
)