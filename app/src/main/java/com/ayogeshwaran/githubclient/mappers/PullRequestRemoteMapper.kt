package com.ayogeshwaran.githubclient.mappers

import com.ayogeshwaran.githubclient.datasource.pr.PullRequestModel
import com.ayogeshwaran.githubclient.network.response.prresponse.github.GithubPrResponse
import javax.inject.Inject

class PullRequestRemoteMapper @Inject constructor() :
    Mapper<GithubPrResponse, PullRequestModel> {
    override fun map(response: GithubPrResponse): PullRequestModel {
        return PullRequestModel(
            title = response.title,
            createdDate = response.created_at,
            closedDate = response.closed_at,
            userName = response.user.login,
            userImageUrl = response.user.avatar_url,
            pullRequestUrl = response.html_url,
            commitsUrl = response.commits_url,
            reviewCommentsUrl = response.review_comments_url,
            commentsUrl = response.comments_url
        )
    }
}