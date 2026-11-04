package hr.foi.popapp.navigation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.foi.popapp.ui.components.PasswordTextField
import hr.foi.popapp.ui.components.StyledButton
import hr.foi.popapp.ui.components.StyledTextField

@Composable
fun LoginPage() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "With your POP-App account you can log in the application. " +
                    "Please note that the course teacher needs to approve your account before you can successfully log in."
        )

        Spacer(modifier = Modifier.height(50.dp))

        StyledTextField(label = "Username", value = username, onValueChange = { username = it })
        PasswordTextField(
            label = "Password",
            value = password,
            onValueChange = { password = it },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.height(50.dp))

        StyledButton(
            label = "Login",
            onClick = { }
        )
    }
}

@Preview
@Composable
fun LoginPagePreview() {
    LoginPage()
}