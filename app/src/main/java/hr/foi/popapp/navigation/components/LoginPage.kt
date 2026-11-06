package hr.foi.popapp.navigation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.foi.popapp.core.LoginHandler
import hr.foi.popapp.core.LoginOutcomeListener
import hr.foi.popapp.login_username_password.UsernamePasswordLoginHandler
import hr.foi.popapp.login_username_password.UsernamePasswordLoginToken
import hr.foi.popapp.ui.components.PasswordTextField
import hr.foi.popapp.ui.components.StyledButton
import hr.foi.popapp.ui.components.StyledTextField

@Composable
fun LoginPage(
    onSuccessfulLogin: () -> Unit,
    loginHandler: LoginHandler
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (errorMessage == "") {
            Text(
                "With your POP-App account you can log in the application. " +
                        "Please note that the course teacher needs to approve your account before you can successfully log in."
            )
        } else {
            Text(
                text = errorMessage,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }

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
            onClick = {
                val usernamePasswordToken = UsernamePasswordLoginToken(
                    username, password
                )
                loginHandler.handleLogin(usernamePasswordToken,
                    object : LoginOutcomeListener {
                        override fun onSuccessfulLogin(username: String) {
                            onSuccessfulLogin()
                        }

                        override fun onFailedLogin(reason: String) {
                            errorMessage = reason
                        }
                    }
                )
            }
        )
    }
}

@Preview
@Composable
fun LoginPagePreview() {
    LoginPage({}, UsernamePasswordLoginHandler())
}