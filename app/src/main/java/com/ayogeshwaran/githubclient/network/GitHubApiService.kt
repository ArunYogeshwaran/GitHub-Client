package com.ayogeshwaran.githubclient.network

import GithubPrResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

private const val GITHUB_JSON_RESPONSE_TYPE = "application/vnd.github.v3+json"

interface GitHubApiService {
    @GET("repos/{userId}/{repoId}/pulls")
    suspend fun getPullRequests(
        @Path("userId") userId: String,
        @Path("repoId") repoId: String,
        @Header("Accept") responseType: String = GITHUB_JSON_RESPONSE_TYPE,
        @Query("state") state: String,
    ): Response<List<GithubPrResponse>>
}