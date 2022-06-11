package com.ayogeshwaran.githubclient.datasource.pr

data class PullRequestModel(
    val title: String,
    val createdDate: String,
    val closedDate: String,
    val userName: String,
    val userImageUrl: String,
    val pullRequestUrl: String
)