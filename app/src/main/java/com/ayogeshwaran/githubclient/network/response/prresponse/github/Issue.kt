package com.ayogeshwaran.githubclient.network.response.prresponse.github

import com.google.gson.annotations.SerializedName

data class Issue(

    @SerializedName("href") val href: String
)