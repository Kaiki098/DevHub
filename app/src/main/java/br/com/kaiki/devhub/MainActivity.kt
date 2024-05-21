package br.com.kaiki.devhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kaiki.devhub.network.GitHubRepository
import br.com.kaiki.devhub.ui.theme.DevHubTheme
import coil.compose.AsyncImage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DevHubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileScreen("batista-s")
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(
    user: String,
    repository: GitHubRepository = GitHubRepository()
) {
    val userFounded by repository
        .findProfileBy(user = user)
        .collectAsState(initial = null)


    userFounded?.let { userProfile ->
        val boxHeight = remember {
            150.dp
        }

        val imageHeight = remember {
            boxHeight
        }

        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp
                        )
                    )
                    .height(boxHeight)
            )
        }

        Column(
            modifier = Modifier
                .padding(top = boxHeight / 2)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = userProfile.avatarUrl,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .height(imageHeight)
                    .clip(shape = RoundedCornerShape(100)),
                placeholder = painterResource(id = R.drawable.cat_selfie)
            )

            Column(
                modifier = Modifier.padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = userProfile.name ?: "Nome não disponível",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
                Text(text = userProfile.login ?: "Login não disponivel", fontWeight = FontWeight.ExtraBold)
            }
            Text(
                text = userProfile.bio ?: "Biografia não disponível",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen("Kaiki098")
}

