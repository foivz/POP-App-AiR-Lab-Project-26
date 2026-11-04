package hr.foi.popapp.navigation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.foi.popapp.ui.components.StyledButton

@Composable
fun PostRegistrationNotice(
    newUsername: String,
    onNoticeUnderstood: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome, $newUsername! Your account has been created. " +
                    "Please wait until the course teacher approves your account. " +
                    "After that, you will be able to log in.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 50.dp)
        )

        StyledButton(label = "I understand", onClick = onNoticeUnderstood)
    }
}

@Preview
@Composable
fun PostRegistrationNoticePreview() {
    PostRegistrationNotice(newUsername = "ihorvat", onNoticeUnderstood = {})
}
