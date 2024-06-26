package br.com.kaiki.devhub

import android.content.Context
import android.content.Intent
import android.content.res.Resources.Theme
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import br.com.kaiki.devhub.ui.MainViewModel
import br.com.kaiki.devhub.ui.theme.DevHubTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        val viewModel: MainViewModel by viewModels()

        setContent {
            val navController = rememberNavController()
            DevHubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DevHubTheme.colorScheme.background
                ) {
                    DevHubApp(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}

fun openWebPage(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

