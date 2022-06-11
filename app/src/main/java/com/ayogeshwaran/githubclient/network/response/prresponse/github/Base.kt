package com.ayogeshwaran.githubclient.network.response.prresponse.github

import com.google.gson.annotations.SerializedName

data class Base(

    @SerializedName("label") val label: String,
    @SerializedName("ref") val ref: String,
    @SerializedName("sha") val sha: String,
    @SerializedName("user") val user: User,
    @SerializedName("repo") val repo: Repo
)