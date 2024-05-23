package br.com.kaiki.devhub.network.model

import br.com.kaiki.devhub.ui.ProfileUiState
import com.google.gson.annotations.SerializedName

data class GitHubProfileWeb(
    val name: String,
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val bio: String,
)

fun GitHubProfileWeb.toProfileUiState(): ProfileUiState {
    return ProfileUiState(
        user = login,
        image = avatarUrl,
        name = name,
        bio = bio,
    )
}