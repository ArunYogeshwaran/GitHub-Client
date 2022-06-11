package com.ayogeshwaran.githubclient.closedpr.usecase

import com.ayogeshwaran.githubclient.common.DispatcherProvider
import com.ayogeshwaran.githubclient.datasource.pr.PullRequestDataSource
import com.ayogeshwaran.githubclient.datasource.pr.PullRequestModel
import com.ayogeshwaran.githubclient.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Use case class containing logic to fetch the Closed Pull Requests from a data source.
 */
class ClosedPrUseCase @Inject constructor(
    private val prRepository: PullRequestDataSource,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend fun getClosedPullRequests(
        userId: String,
        repoId: String
    ): Flow<NetworkResult<List<PullRequestModel>>> {
        return withContext(dispatcherProvider.io()) {
            prRepository.getClosedPullRequests(userId, repoId)
        }
    }
}
