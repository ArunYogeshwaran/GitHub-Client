package com.ayogeshwaran.githubclient.closedpr

/**
 * Model to represent the UI.
 */
data class UIClosedPullRequest(
    val title: String,
    val createdDate: String,
    val closedDate: String,
    val userName: String,
    val userImageUrl: String,
    val pullRequestUrl: String
)
