package br.com.kaiki.devhub.network

import android.util.Log
import br.com.kaiki.devhub.network.model.toGitHubRepository
import br.com.kaiki.devhub.network.model.toProfileUiState
import br.com.kaiki.devhub.ui.ProfileUiState

class GitHubWebRepository(
    private val service: GitHubService = RetrofitInit().gitHubService
) {

    suspend fun findProfileBy(user: String): ProfileUiState {
        try {
            val profile = service.getUser(user).toProfileUiState()
            val repositories = service.getRepos(user).map { it.toGitHubRepository() }
            return profile.copy(repositories = repositories)
        } catch (e: Exception) {
            Log.e("GitHubWebRepository", "findProfileBy: Failed to search for user", e)
            throw e
        }
    }
}