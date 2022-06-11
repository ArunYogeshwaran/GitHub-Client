package com.ayogeshwaran.githubclient.network.response.prresponse.github

import com.google.gson.annotations.SerializedName

data class Commits(

    @SerializedName("href") val href: String
)