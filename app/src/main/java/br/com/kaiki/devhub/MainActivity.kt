package br.com.kaiki.devhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.kaiki.devhub.ui.theme.DevHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevHubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                     Column {
                         Image(painter = painterResource(id = R.drawable.cat_selfie), contentDescription = "A zoom in cat photo")
                         Text(text = "Kaiki Alvarenga de Souza")
                         Text(text = "Kaiki098")
                         Text(text = "Computer Science student at IFSULDEMINAS - PASSOS")
                     }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Column {
        Image(painter = painterResource(id = R.drawable.cat_selfie), contentDescription = "A zoom in cat photo")
        Text(text = "Kaiki Alvarenga de Souza")
        Text(text = "Kaiki098")
        Text(text = "Computer Science student at IFSULDEMINAS - PASSOS")
    }
}
