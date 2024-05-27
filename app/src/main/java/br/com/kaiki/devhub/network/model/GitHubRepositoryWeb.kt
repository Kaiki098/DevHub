package br.com.kaiki.devhub.network.model

import br.com.kaiki.devhub.model.GitHubRepository
import com.google.gson.annotations.SerializedName

data class GitHubRepositoryWeb (
    val name: String,
    val description: String?,
    @SerializedName("html_url")
    val url: String
)

fun GitHubRepositoryWeb.toGitHubRepository() = GitHubRepository (
    name = name,
    description = description ?: "",
    url = url
)