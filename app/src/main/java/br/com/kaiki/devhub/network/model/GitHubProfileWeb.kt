package br.com.kaiki.devhub.network.model

import br.com.kaiki.devhub.ui.screen.ProfileUiState
import com.google.gson.annotations.SerializedName

data class GitHubProfileWeb(
    val name: String? = null,
    val login: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    val bio: String? = null,
)

fun GitHubProfileWeb.toProfileUiState(): ProfileUiState {
    return ProfileUiState(
        user = login ?: "Nome de usuário não disponível",
        image = avatarUrl,
        name = name ?: "Nome não disponível",
        bio = bio ?: "Bio não disponível",
    )
}