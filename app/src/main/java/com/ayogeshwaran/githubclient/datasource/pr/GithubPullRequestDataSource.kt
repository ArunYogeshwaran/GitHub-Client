package com.ayogeshwaran.githubclient.datasource.pr

import GithubPrResponse
import com.ayogeshwaran.githubclient.network.GitHubApiService
import retrofit2.Response
import javax.inject.Inject

class GithubPullRequestDataSource @Inject constructor(
    private val gitHubApiService: GitHubApiService
) : PullRequestDataSource {
    override suspend fun getClosedPullRequests(
        userId: String,
        repoId: String
    ): List<PullRequestModel> {
        val response: Response<List<GithubPrResponse>> = this.gitHubApiService.getPullRequests(
            userId = userId, repoId = repoId, state = GitHubPullRequestState.CLOSED.state
        )
        return response.body()?.map {
            PullRequestModel(
                it.title,
                it.created_at,
                it.closed_at,
                it.user.login,
                it.user.avatar_url
            )
        } ?: emptyList()
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