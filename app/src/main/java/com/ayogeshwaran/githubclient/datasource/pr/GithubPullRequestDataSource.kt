package com.ayogeshwaran.githubclient.datasource.pr

import com.ayogeshwaran.githubclient.mappers.PullRequestRemoteMapper
import com.ayogeshwaran.githubclient.network.GitHubApiService
import com.ayogeshwaran.githubclient.network.response.prresponse.github.GithubPrResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class GithubPullRequestDataSource @Inject constructor(
    private val gitHubApiService: GitHubApiService,
    private val domainMapper: PullRequestRemoteMapper
) : PullRequestDataSource {
    override suspend fun getClosedPullRequests(
        userId: String,
        repoId: String
    ): List<PullRequestModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<GithubPrResponse>> = gitHubApiService.getPullRequests(
                userId = userId, repoId = repoId, state = GitHubPullRequestState.CLOSED.state
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    it.map { response ->
                        domainMapper.map(response)
                    }
                } ?: emptyList()
            } else {
                emptyList()
            }
        }
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