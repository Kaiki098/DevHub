package br.com.kaiki.devhub.network

import android.util.Log
import kotlinx.coroutines.flow.flow

class GitHubRepository(
    private val service: GitHubService = RetrofitInit().gitHubService
) {
    fun findProfileBy(user: String) = flow {
        try {
            emit(service.getUser(user))
        } catch (e: Exception) {
            Log.e("GitHubWebClient", "findProfileBy: falha ao buscar usuário", e)
        }
    }
}