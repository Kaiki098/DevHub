package br.com.kaiki.devhub

import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.lifecycleScope
import br.com.kaiki.devhub.network.GitHubProfileWeb
import br.com.kaiki.devhub.network.RetrofitInit
import br.com.kaiki.devhub.ui.theme.DevHubTheme
import coil.compose.AsyncImage
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = RetrofitInit()
        var response: GitHubProfileWeb
        lifecycleScope.launch {
            response = retrofit.gitHubService.getUser("Kaiki098")
            Log.d("API", "$response")
        }



        setContent {
            DevHubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun ProfileScreen() {
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

    Column (
        modifier = Modifier
            .padding(top = boxHeight / 2)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = "https://avatars.githubusercontent.com/u/127666620?v=4",
            contentDescription = "Profile picture",
            modifier = Modifier
                .height(imageHeight)
                .clip(shape = RoundedCornerShape(100)),
            placeholder = painterResource(id = R.drawable.cat_selfie)
        )

        Column (
            modifier = Modifier.padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Kaiki Alvarenga de Souza", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
            Text(text = "Kaiki098", fontWeight = FontWeight.ExtraBold)
        }
        Text(text = "Computer Science student at IFSULDEMINAS - PASSOS", textAlign = TextAlign.Center)
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}

