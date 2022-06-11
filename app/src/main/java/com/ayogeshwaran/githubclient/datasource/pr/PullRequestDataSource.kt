package com.ayogeshwaran.githubclient.datasource.pr

import com.ayogeshwaran.githubclient.network.NetworkResult
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow

@Module
@InstallIn(ViewModelComponent::class)
abstract class PullRequestRepoModule {
    @Binds
    abstract fun bindPullRequestDataSource(impl: GithubPullRequestDataSource): PullRequestDataSource
}

interface PullRequestDataSource {
    suspend fun getClosedPullRequests(
        userId: String,
        repoId: String
    ): Flow<NetworkResult<List<PullRequestModel>>>
}