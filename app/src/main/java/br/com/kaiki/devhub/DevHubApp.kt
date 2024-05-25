package br.com.kaiki.devhub

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.kaiki.devhub.ui.MainViewModel
import br.com.kaiki.devhub.ui.screen.ProfileScreen
import br.com.kaiki.devhub.ui.screen.UserInputScreen

@Composable
fun DevHubApp(
    navController: NavHostController,
    viewModel: MainViewModel
) {

    NavHost(navController = navController, startDestination = "userinputscreen") {
        composable(route = "userinputscreen") {
            UserInputScreen(
                viewModel = viewModel,
                navigateToProfile = {
                    navController.navigate("profilescreen")
                }
            )

        }
        composable(route = "profilescreen"){
            ProfileScreen(viewModel = viewModel, navigateToUserInput = {
                navController.navigate("userinputscreen")
            })
        }
    }

}