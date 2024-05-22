package br.com.kaiki.devhub

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import br.com.kaiki.devhub.ui.MainViewModel
import br.com.kaiki.devhub.ui.screen.ProfileScreen

@Composable
fun DevHubApp(viewModel: MainViewModel) {
    val user = "Kaiki098"
    LaunchedEffect(user) {
        viewModel.fetchProfileData(user)
    }

    ProfileScreen(viewModel = viewModel)
}