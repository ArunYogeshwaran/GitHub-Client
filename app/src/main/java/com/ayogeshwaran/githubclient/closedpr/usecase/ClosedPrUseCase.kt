package com.ayogeshwaran.githubclient.closedpr.usecase

import com.ayogeshwaran.githubclient.datasource.pr.PullRequestDataSource
import com.ayogeshwaran.githubclient.datasource.pr.PullRequestModel
import javax.inject.Inject

/**
 * Use case class containing logic to fetch the Closed Pull Requests from a data source.
 */
class ClosedPrUseCase @Inject constructor(private val prRepository: PullRequestDataSource) {
    suspend fun getClosedPullRequests(): List<PullRequestModel> {
        val userId = "ArunYogeshwaran"
        val repoId = "GitHub-Client"
        return prRepository.getClosedPullRequests(userId, repoId)
    }
}
