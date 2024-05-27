package br.com.kaiki.devhub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kaiki.devhub.model.GitHubRepository
import br.com.kaiki.devhub.ui.theme.DevHubTheme

@Composable
fun RepositoryItem(repo: GitHubRepository) {

    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = DevHubTheme.colorScheme.card
        )
    ) {
        Column (
            Modifier.background(DevHubTheme.colorScheme.card)
        ) {
            Text(
                repo.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DevHubTheme.colorScheme.primary)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = DevHubTheme.colorScheme.onPrimary
            )
            if (repo.description.isNotBlank()) {
                Text(
                    repo.description,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    lineHeight = 20.sp,
                    color = DevHubTheme.colorScheme.onBackground
                )
            }
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        end = 8.dp,
                        bottom = 8.dp,
                        top = if (repo.description.isNotBlank()) 0.dp else 8.dp
                    )
            ) {
                Text(text = "Visit", color = DevHubTheme.colorScheme.onCard)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryItemPreview() {
    RepositoryItem(
        repo = GitHubRepository(
            name = "7-DAYS-OF-CODE-RESPONSIVIDADE",
            description = "Aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum qui"
        )
    )
}

