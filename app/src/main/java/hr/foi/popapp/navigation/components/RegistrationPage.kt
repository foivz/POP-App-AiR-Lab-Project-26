package hr.foi.popapp.navigation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.foi.popapp.models.Role
import hr.foi.popapp.models.availableRoles
import hr.foi.popapp.network.NetworkService
import hr.foi.popapp.network.models.RegistrationBody
import hr.foi.popapp.network.models.ResponseBody
import hr.foi.popapp.ui.components.PasswordTextField
import hr.foi.popapp.ui.components.RoleSelector
import hr.foi.popapp.ui.components.StyledButton
import hr.foi.popapp.ui.components.StyledTextField
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RegistrationPage(
    onSuccessfulRegistration: (newUsername: String) -> Unit
) {
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var username by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    var isAwaitingResponse by remember {
        mutableStateOf(false)
    }
    var errorMessage by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Create an Account",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        if (errorMessage != "") {
            Text(
                text = errorMessage,
                color = Color.Red
            )
        }

        StyledTextField(label = "First name", value = firstName, onValueChange = { firstName = it })

        StyledTextField(label = "Last name", value = lastName, onValueChange = { lastName = it })

        StyledTextField(label = "Username", value = username, onValueChange = { username = it })

        StyledTextField(label = "Email", value = email, onValueChange = { email = it })

        PasswordTextField(label = "Password", value = password, onValueChange = { password = it })

        PasswordTextField(
            label = "Confirm password",
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

        var selectedRole by remember { mutableStateOf<Role>(availableRoles[0])}
        RoleSelector(
            roles = availableRoles,
            selected = selectedRole,
            onRoleSelected = { selectedRole = it }
        )

        Spacer(modifier = Modifier.height(50.dp))

        StyledButton(
            label = "Register",
            enabled = !isAwaitingResponse,
            onClick = {
                val requestBody = RegistrationBody(
                    firstName, lastName, username, email, password, selectedRole.apiValue
                )

                isAwaitingResponse = true
                errorMessage = ""

                NetworkService.authService.registerUser(requestBody).enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        isAwaitingResponse = false
                        if (response.body()?.success == true) {
                            onSuccessfulRegistration(username)
                        } else {
                            errorMessage = "Registration failed. Check the entered data."
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        isAwaitingResponse = false
                        errorMessage = "Couldn't contact the server."
                    }
                })
            }
        )
    }
}

@Preview
@Composable
fun RegistrationPagePreview() {
    RegistrationPage({})
}