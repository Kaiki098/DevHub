package br.com.kaiki.devhub.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kaiki.devhub.R
import br.com.kaiki.devhub.model.GitHubRepository
import br.com.kaiki.devhub.ui.MainViewModel
import br.com.kaiki.devhub.ui.ProfileUiState
import br.com.kaiki.devhub.ui.components.RepositoryItem
import br.com.kaiki.devhub.ui.theme.DevHubTheme
import coil.compose.AsyncImage


@Composable
fun ProfileScreen(
    viewModel: MainViewModel,
    navigateToUserInput: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    BackHandler {
        navigateToUserInput()
        viewModel.resetUiState()
    }

    if (uiState.isDataFetched) {
        Profile(uiState = uiState)
    }
}

@Composable
fun Profile(uiState: ProfileUiState) {
    LazyColumn {
        item {
            ProfileHeader(state = uiState)
        }
        item {
            if (uiState.repositories.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = DevHubTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Repositórios", Modifier.padding(8.dp),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = DevHubTheme.colorScheme.onPrimary,
                        lineHeight = 32.sp,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        items(uiState.repositories) { repo ->
            RepositoryItem(repo = repo)
        }
    }
}

@Composable
fun ProfileHeader(state: ProfileUiState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val boxHeight = remember {
            150.dp
        }

        val imageHeight = remember {
            boxHeight
        }

        var boxSize by remember {
            mutableStateOf(IntSize.Zero)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    boxSize = coordinates.size
                }
                .background(
                    brush = Brush.sweepGradient(
                        colors = DevHubTheme.colorScheme.gradientColors,
                        center = Offset(x = boxSize.width / 2f, y = boxSize.height.toFloat())
                    ),
                    shape = RoundedCornerShape(
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                )
                .height(boxHeight)
        ) {
            //AsyncImagePainter.State.Loading
            AsyncImage(
                model = state.image,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(imageHeight)
                    .offset(y = imageHeight / 2)
                    .clip(shape = RoundedCornerShape(100))
                    .align(Alignment.BottomCenter),
                placeholder = painterResource(id = R.drawable.blank_profilepic)
            )
        }
        Spacer(modifier = Modifier.height(imageHeight / 2))
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = state.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DevHubTheme.colorScheme.onBackground
            )
            Text(
                text = state.user,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = DevHubTheme.colorScheme.onBackground
            )
        }
        Text(
            text = state.bio,
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    bottom = 8.dp,
                    end = 8.dp
                )
                .heightIn(min = 100.dp),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = DevHubTheme.colorScheme.onBackground
        )

    }

}


//@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(
        uiState = ProfileUiState(
            "Kaiki Alvarenga de Souza",
            "Kaiki098",
            "https://avatars.githubusercontent.com/u/127666620?v=4",
            "Cientista da Computação 2/8"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileWithRepositoriesPreview() {
    Profile(
        uiState = ProfileUiState(
            user = "Kaiki098",
            image = "https://avatars.githubusercontent.com/u/127666620?v=4",
            name = "Kaiki Alvarenga de Souza",
            bio = "Cientista da Computação 2/8",
            repositories = listOf(
                GitHubRepository(
                    name = "github-compose"
                ),
                GitHubRepository(
                    name = "ceep-compose",
                    description = "Sample project to practice the Jetpack Compose Apps"
                ),
                GitHubRepository(
                    name = "orgs-jetpack-compose",
                    description = "Projeto de simulação do e-commerce de produtos naturais com a finalidade de treinar o Jetpack Compose"
                )
            )
        )
    )
}


