package com.ayogeshwaran.githubclient.network.response.prresponse.github

import com.google.gson.annotations.SerializedName

data class ReviewComments(

    @SerializedName("href") val href: String
)