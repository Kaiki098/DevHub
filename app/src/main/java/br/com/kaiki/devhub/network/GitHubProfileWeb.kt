package br.com.kaiki.devhub.network

import com.google.gson.annotations.SerializedName

data class GitHubProfileWeb(
    val name: String,
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val bio: String
)