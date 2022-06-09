package com.ayogeshwaran.githubclient.datasource.pr

import com.ayogeshwaran.githubclient.network.GitHubApiService
import javax.inject.Inject

class GithubPullRequestDataSource @Inject constructor(
    private val gitHubApiService: GitHubApiService
) : PullRequestDataSource {
    override suspend fun getClosedPullRequests(
        userId: String,
        repoId: String
    ): List<PullRequestModel> {
        val response = gitHubApiService.getPullRequests(
            userId = userId, repoId = repoId, state = GitHubPullRequestState.CLOSED.state
        )
        return emptyList()
    }
}

/**
 * Docs here - https://docs.github.com/en/rest/pulls/pulls
 */
enum class GitHubPullRequestState(val state: String) {
    OPEN("open"),
    CLOSED("closed"),
    ALL("all")
}