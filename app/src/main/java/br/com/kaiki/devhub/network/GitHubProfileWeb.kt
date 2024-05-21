package br.com.kaiki.devhub.network

import br.com.kaiki.devhub.ui.screen.ProfileUiState
import com.google.gson.annotations.SerializedName

data class GitHubProfileWeb(
    val name: String?,
    val login: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val bio: String?
)

fun GitHubProfileWeb.toProfileUiState(): ProfileUiState {
    return ProfileUiState(
        user = login ?: "Nome de usuário não disponível",
        image = avatarUrl,
        name = name ?: "Nome não disponível",
        bio = bio ?: "Bio não disponível"
    )
}