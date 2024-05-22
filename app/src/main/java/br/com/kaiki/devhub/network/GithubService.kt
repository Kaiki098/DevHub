package br.com.kaiki.devhub.network

import br.com.kaiki.devhub.network.model.GitHubProfileWeb
import br.com.kaiki.devhub.network.model.GitHubRepositoryWeb
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}")
    suspend fun getUser(@Path("user") user: String): GitHubProfileWeb

    @GET("users/{user}/repos")
    suspend fun getRepos(@Path("user") user: String): List<GitHubRepositoryWeb>
}