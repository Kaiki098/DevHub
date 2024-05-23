package br.com.kaiki.devhub.ui

import android.util.Log
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.kaiki.devhub.model.GitHubRepository
import br.com.kaiki.devhub.network.GitHubWebRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Error

data class ProfileUiState(
    val name: String = "",
    val user: String = "",
    val image: String = "",
    val bio: String = "",
    val repositories: List<GitHubRepository> = emptyList(),
    val error: Boolean = false
)

class MainViewModel(
    private val repository: GitHubWebRepository = GitHubWebRepository()
): ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun fetchProfileData(user: String) {
        viewModelScope.launch {
            try {
                val profile = repository.findProfileBy(user)
                _uiState.value = profile
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching profile data", e)
                _uiState.value = ProfileUiState(error = true)
            }
        }
    }

}
