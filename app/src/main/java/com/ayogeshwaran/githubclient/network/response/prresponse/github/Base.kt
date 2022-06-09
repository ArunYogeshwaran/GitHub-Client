package com.ayogeshwaran.githubclient.network.response.prresponse.github

data class Base(
    val label: String,
    val ref: String,
    val repo: Repo,
    val sha: String,
    val user: User
)