package br.com.kaiki.devhub.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kaiki.devhub.R
import br.com.kaiki.devhub.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputScreen(viewModel: MainViewModel, navigateToProfile: () -> Unit) {
    var userName by remember {
        mutableStateOf("")
    }

    var textFieldError by remember {
        mutableStateOf(false)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    val uiState by viewModel.uiState.collectAsState()

    Log.d("ResetUi", "Input Screen")
    if (uiState.isDataFetched) {
        navigateToProfile()
    }

    Box (
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.devhub),
            contentDescription = "DevHub logo",
            Modifier.padding(top = 32.dp)
        )
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(
                text = "Search for any GitHub account",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Enter a user name to see the information",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(top = 4.dp)
            )
        }

        if (isLoading) {
            CircularProgressIndicator(Modifier.height(50.dp))
        } else {
            Spacer(modifier = Modifier.height(50.dp))
        }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(320.dp)
        ) {
            OutlinedTextField(
                value = userName,
                onValueChange = {
                    userName = it
                },
                label = { Text(text = "User name", fontSize = 14.sp)},
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.LightGray,
                    unfocusedLabelColor = Color.Gray
                ),
                shape = RoundedCornerShape(8.dp),
                isError = textFieldError,
                supportingText = {
                    if (textFieldError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Please enter a valid user name",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
            Button(
                onClick = {
                    if (userName.isNotEmpty()){
                        isLoading = true
                        viewModel.fetchProfileData(userName)
                    } else {
                        textFieldError = true
                    }
                },
                colors = ButtonDefaults
                    .buttonColors(Color(0xFF0B004F)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .height(TextFieldDefaults.MinHeight),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Search user", fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserInputScreenPreview() {
    val fakeViewModel = MainViewModel()
    UserInputScreen(fakeViewModel, {})
}