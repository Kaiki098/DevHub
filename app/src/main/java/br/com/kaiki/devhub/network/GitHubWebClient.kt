package br.com.kaiki.devhub.network

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.com.kaiki.devhub.network.model.toGitHubRepository
import br.com.kaiki.devhub.network.model.toProfileUiState
import br.com.kaiki.devhub.ui.screen.ProfileUiState

class GitHubWebClient(
    private val service: GitHubService = RetrofitInit().gitHubService
) {
    var uiState by mutableStateOf(ProfileUiState())
        private set

    suspend fun findProfileBy(user: String) {
        try {
            val profile = service.getUser(user).toProfileUiState()
            val repositories = service.getRepos(user).map { it.toGitHubRepository() }
            uiState = profile.copy(repositories = repositories)
        } catch (e: Exception) {
            Log.e("GitHubWebClient", "findProfileBy: Failed to search for user", e)
        }
    }
}