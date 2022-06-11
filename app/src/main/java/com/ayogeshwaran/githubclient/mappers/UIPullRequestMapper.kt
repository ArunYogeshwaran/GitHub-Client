package com.ayogeshwaran.githubclient.mappers

import com.ayogeshwaran.githubclient.closedpr.UIClosedPullRequest
import com.ayogeshwaran.githubclient.datasource.pr.PullRequestModel
import javax.inject.Inject

class UIPullRequestMapper @Inject constructor() :
    Mapper<PullRequestModel, UIClosedPullRequest> {
    override fun map(model: PullRequestModel): UIClosedPullRequest {
        return UIClosedPullRequest(
            title = model.title,
            createdDate = model.createdDate,
            closedDate = model.closedDate,
            userName = model.userName,
            userImageUrl = model.userImageUrl,
            pullRequestUrl = model.pullRequestUrl
        )
    }
}
