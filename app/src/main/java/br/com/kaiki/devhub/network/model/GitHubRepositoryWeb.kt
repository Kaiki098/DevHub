package br.com.kaiki.devhub.network.model

import br.com.kaiki.devhub.model.GitHubRepository

data class GitHubRepositoryWeb (
    val name: String,
    val description: String?
)

fun GitHubRepositoryWeb.toGitHubRepository() = GitHubRepository (
    name = name,
    description = description ?: ""
)