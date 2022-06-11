package com.ayogeshwaran.githubclient.network.response.prresponse.github

import com.google.gson.annotations.SerializedName

data class ReviewComment(

    @SerializedName("href") val href: String
)