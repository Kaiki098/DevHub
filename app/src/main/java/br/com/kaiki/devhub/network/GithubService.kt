package br.com.kaiki.devhub.network

import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}")
    suspend fun getUser(@Path("user") user: String): GitHubProfileWeb
}