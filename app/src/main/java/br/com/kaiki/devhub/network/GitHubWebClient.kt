package br.com.kaiki.devhub.network

import android.util.Log
import br.com.kaiki.devhub.network.model.toGitHubRepository
import br.com.kaiki.devhub.network.model.toProfileUiState
import br.com.kaiki.devhub.ui.ProfileUiState

class GitHubWebClient(
    private val service: GitHubService = RetrofitInit().gitHubService
) {

    suspend fun findProfileBy(user: String): ProfileUiState {
        try {
            Log.d("Fetching", "Fetching")
            val profile = service.getUser(user).toProfileUiState()
            val repositories = service.getRepos(user).map { it.toGitHubRepository() }
            return profile.copy(repositories = repositories)
        } catch (e: Exception) {
            Log.e("GitHubWebClient", "findProfileBy: Failed to search for user", e)
            throw e
        }
    }
}