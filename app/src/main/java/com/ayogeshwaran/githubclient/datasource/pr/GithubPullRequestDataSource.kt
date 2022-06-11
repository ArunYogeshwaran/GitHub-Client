package com.ayogeshwaran.githubclient.datasource.pr

import com.ayogeshwaran.githubclient.mappers.PullRequestRemoteMapper
import com.ayogeshwaran.githubclient.network.ErrorCode
import com.ayogeshwaran.githubclient.network.GitHubApiService
import com.ayogeshwaran.githubclient.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GithubPullRequestDataSource @Inject constructor(
    private val gitHubApiService: GitHubApiService,
    private val domainMapper: PullRequestRemoteMapper
) : PullRequestDataSource {
    override suspend fun getClosedPullRequests(
        userId: String,
        repoId: String
    ): Flow<NetworkResult<List<PullRequestModel>>> {
        return flow {
            emit(NetworkResult.Loading())

            val response = gitHubApiService.getPullRequests(
                userId = userId, repoId = repoId, state = GitHubPullRequestState.CLOSED.state
            )

            if (response.isSuccessful) {
                val body = response.body()
                val list = body?.let {
                    it.map { response ->
                        domainMapper.map(response)
                    }
                } ?: emptyList()
                emit(NetworkResult.Success(list))
            } else {
                emit(NetworkResult.Error(ErrorCode.REQUEST_FAILED))
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